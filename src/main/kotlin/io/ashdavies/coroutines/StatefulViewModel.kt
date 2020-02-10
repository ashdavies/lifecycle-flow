package io.ashdavies.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Action
import io.ashdavies.architecture.Signal
import io.ashdavies.architecture.State
import io.ashdavies.signal.SignalDispatcher
import io.ashdavies.signal.SignalStore
import io.ashdavies.state.StateMachine
import io.ashdavies.state.StateMachinery
import io.ashdavies.state.StateReducer
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalLifecycleApi
abstract class StatefulViewModel<S : State, T : Signal>(
    state: S,
    signal: T,
    reducer: StateReducer<S, T>
) : SignalStore<T> by SignalDispatcher(signal),
    StateMachine<S> by StateMachinery(state),
    ViewModel() {

  init {
    viewModelScope.launch {
      signals.collect { signal ->
        action(object : Action<S> {
          override suspend fun invoke(result: Result<S>): S {
            return reducer(result.getOrThrow(), signal)
          }
        })
      }
    }
  }
}
