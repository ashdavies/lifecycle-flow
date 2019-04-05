package io.ashdavies.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.Event

interface ActivityCommander {

  val commands: LiveData<Event<ActivityCommand>>

  fun dispatch(command: ActivityCommand)

  class Standard : ActivityCommander {

    private val _commands: MutableLiveData<Event<ActivityCommand>> = mutableLiveDataOf()
    override val commands: LiveData<Event<ActivityCommand>> = _commands

    override fun dispatch(command: ActivityCommand) {
      _commands.value = Event(command)
    }
  }
}
