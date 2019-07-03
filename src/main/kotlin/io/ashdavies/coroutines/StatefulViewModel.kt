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
abstract class StatefulViewModel<S, T : Signal>(
    initial: S,
    reducer: StateReducer<S, T>
) : SignalStore<T> by SignalDispatcher<T>(),
    StateMachine<S> by StateMachinery(initial),
    ScopedViewModel() {

  private val _reducer: StateReducer<S, T> = reducer

  fun reduce(value: T) {
    ImmediateScope.launch {
      action {
        _reducer(it.getOrThrow(), value)
      }
    }
  }
}
