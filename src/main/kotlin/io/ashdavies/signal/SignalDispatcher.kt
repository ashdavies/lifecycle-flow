package io.ashdavies.signal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.architecture.Event
import io.ashdavies.architecture.Signal
import io.ashdavies.extensions.mutableLiveData

class SignalDispatcher<T : Signal> private constructor(private val _signals: MutableLiveData<Event<T>>) : SignalStore<T> {

  constructor(value: T) : this(mutableLiveData(Event(value)))

  constructor() : this(mutableLiveData())

  override val signals: LiveData<Event<T>> = _signals

  override fun signal(signal: T) {
    _signals.value = Event(signal)
  }
}
