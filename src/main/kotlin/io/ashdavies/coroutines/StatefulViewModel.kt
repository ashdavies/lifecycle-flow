package io.ashdavies.coroutines

import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Event
import io.ashdavies.architecture.Signal
import io.ashdavies.architecture.State
import io.ashdavies.extensions.mediatorLiveData
import io.ashdavies.lifecycle.LiveDataScope
import io.ashdavies.operator.Operator
import io.ashdavies.signal.SignalDispatcher
import io.ashdavies.signal.SignalStore
import io.ashdavies.state.StateMachine
import io.ashdavies.state.StateMachinery
import io.ashdavies.state.StateReducer
import kotlinx.coroutines.launch

@ExperimentalLifecycleApi
abstract class StatefulViewModel<S : State, T : Signal>(
    initial: S,
    reducer: StateReducer<S, T>
) : SignalStore<T> by SignalDispatcher(),
    StateMachine<S> by StateMachinery(initial),
    ScopedViewModel() {

  private val _reducer: StateReducer<S, T> = reducer

  init {
    mediatorLiveData(signals, object : Operator<Event<T>, S> {
      override fun invoke(scope: LiveDataScope<S>, value: Event<T>) {
        ImmediateScope.launch {
          action {
            _reducer(it.getOrThrow(), value.peek())
          }
        }
      }
    })
  }

  fun reduce(signal: T) {
    ImmediateScope.launch {
      action {
        _reducer(it.getOrThrow(), signal)
      }
    }
  }
}
