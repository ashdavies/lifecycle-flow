package io.ashdavies.testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveData
import io.ashdavies.lifecycle.Event
import io.ashdavies.navigation.ActivityCommand
import io.ashdavies.navigation.ActivityCommander

internal class FakeActivityCommander : ActivityCommander {

  private val _commands: MutableLiveData<Event<ActivityCommand>> = mutableLiveData()
  override val commands: LiveData<Event<ActivityCommand>> = _commands

  override fun dispatch(command: ActivityCommand) {
    _commands.value = Event(command)
  }
}
