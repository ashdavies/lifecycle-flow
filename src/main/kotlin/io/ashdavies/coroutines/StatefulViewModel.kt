package io.ashdavies.coroutines

import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Signal
import io.ashdavies.signal.SignalDispatcher
import io.ashdavies.signal.SignalStore
import io.ashdavies.state.StateMachine
import io.ashdavies.state.StateMachinery
import io.ashdavies.state.StateReducer
import kotlinx.coroutines.launch

@ExperimentalLifecycleApi
abstract class StatefulViewModel<S, T : Signal>(initial: S) :
    SignalStore<T> by SignalDispatcher<T>(),
    StateMachine<S> by StateMachinery(initial),
    ScopedViewModel() {

  protected abstract val reducer: StateReducer<S, T>

  fun reduce(value: T) {
    ImmediateScope.launch {
      action {
        reducer(it.getOrThrow(), value)
      }
    }
  }
}
