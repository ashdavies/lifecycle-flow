package io.ashdavies.signal

import io.ashdavies.architecture.Signal
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

@FlowPreview
class SignalDispatcher<T : Signal> : SignalStore<T> {

  private val _signals: Channel<T> = Channel(CONFLATED)
  override val signals: Flow<T> = _signals.consumeAsFlow()

  constructor(value: T) {
    _signals.offer(value)
  }

  override suspend fun signal(signal: T) {
    _signals.offer(signal)
  }
}
