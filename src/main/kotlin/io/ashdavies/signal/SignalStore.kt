package io.ashdavies.signal

import io.ashdavies.architecture.Signal
import kotlinx.coroutines.flow.Flow

interface SignalStore<T : Signal> {

  val signals: Flow<T>

  suspend fun signal(signal: T)
}
