package io.ashdavies.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.Event

internal class DispatchingCommander<T : LifecycleOwner> : LifecycleCommander<T> {

  private val _events: MutableLiveData<Event<LifecycleCommand<T>>> = mutableLiveDataOf()
  override val events: LiveData<Event<LifecycleCommand<T>>> = _events

  override fun dispatch(command: LifecycleCommand<T>) {
    _events.value = Event(command)
  }
}
