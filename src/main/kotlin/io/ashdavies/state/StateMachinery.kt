package io.ashdavies.state

import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Action
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

@FlowPreview
@ExperimentalLifecycleApi
class StateMachinery<T>(initial: T) : StateMachine<T> {

  private val _state: Channel<Result<T>> = Channel(CONFLATED)
  override val state: Flow<Result<T>> = _state.consumeAsFlow()

  private val _loading: Channel<Boolean> = Channel(CONFLATED)
  override val loading: Flow<Boolean> = _loading.consumeAsFlow()

  init {
    _state.offer(Result.success(initial))
    _loading.offer(false)
  }

  override suspend fun action(action: Action<T>) = runLoading {
    runCatching {
      action(_state.receive())
    }
  }

  private suspend inline fun <S> S.runLoading(block: S.() -> Result<T>) {
    _loading.send(true)
    _state.send(block())
    _loading.send(false)
  }
}
