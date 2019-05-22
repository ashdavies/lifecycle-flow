package io.ashdavies.signal

import androidx.lifecycle.LiveData
import io.ashdavies.architecture.Event
import io.ashdavies.architecture.Signal

interface SignalStore<T : Signal> {

  val signals: LiveData<Event<T>>

  fun signal(signal: T)
}
