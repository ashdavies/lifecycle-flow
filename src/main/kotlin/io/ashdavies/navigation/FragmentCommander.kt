package io.ashdavies.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.LiveDataEvent

interface FragmentCommander {

  val commands: LiveData<LiveDataEvent<FragmentCommand>>

  fun dispatch(command: ActivityCommand)

  fun dispatch(command: FragmentCommand)

  class Standard : FragmentCommander {

    private val _commands: MutableLiveData<LiveDataEvent<FragmentCommand>> = mutableLiveDataOf()
    override val commands: LiveData<LiveDataEvent<FragmentCommand>> = _commands

    override fun dispatch(command: ActivityCommand) {
      _commands.value = LiveDataEvent(CompatCommand(command))
    }

    override fun dispatch(command: FragmentCommand) {
      _commands.value = LiveDataEvent(command)
    }
  }
}
