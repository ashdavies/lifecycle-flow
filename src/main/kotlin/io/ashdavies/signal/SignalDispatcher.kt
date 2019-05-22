package io.ashdavies.signal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.architecture.Event
import io.ashdavies.architecture.Signal

class SignalDispatcher<T : Signal> : SignalStore<T> {

  private val _signals: MutableLiveData<Event<T>> = MutableLiveData()
  override val signals: LiveData<Event<T>> = _signals

  override fun signal(signal: T) {
    _signals.value = Event(signal)
  }
}
