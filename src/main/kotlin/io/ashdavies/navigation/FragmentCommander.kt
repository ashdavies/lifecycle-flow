package io.ashdavies.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.Event

interface FragmentCommander {

  val commands: LiveData<Event<FragmentCommand>>

  fun dispatch(command: ActivityCommand)

  fun dispatch(command: FragmentCommand)

  class Standard : FragmentCommander {

    private val _commands: MutableLiveData<Event<FragmentCommand>> = mutableLiveDataOf()
    override val commands: LiveData<Event<FragmentCommand>> = _commands

    override fun dispatch(command: ActivityCommand) {
      _commands.value = Event(CompatCommand(command))
    }

    override fun dispatch(command: FragmentCommand) {
      _commands.value = Event(command)
    }
  }
}
