package io.ashdavies.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.Event

internal class DispatchingCommander<T : LifecycleOwner> : LifecycleCommander<T> {

  private val _commands: MutableLiveData<Event<LifecycleCommand<T>>> = mutableLiveDataOf()
  override val commands: LiveData<Event<LifecycleCommand<T>>> = _commands

  override fun dispatch(command: LifecycleCommand<T>) {
    _commands.value = Event(command)
  }
}
