package io.ashdavies.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.LiveDataEvent

interface ActivityCommander {

  val commands: LiveData<LiveDataEvent<ActivityCommand>>

  fun dispatch(command: ActivityCommand)

  class Standard : ActivityCommander {

    private val _commands: MutableLiveData<LiveDataEvent<ActivityCommand>> = mutableLiveDataOf()
    override val commands: LiveData<LiveDataEvent<ActivityCommand>> = _commands

    override fun dispatch(command: ActivityCommand) {
      _commands.value = LiveDataEvent(command)
    }
  }
}
