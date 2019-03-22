package io.ashdavies.testing

import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.LiveDataEvent
import io.ashdavies.navigation.ActivityCommand
import io.ashdavies.navigation.CompatCommand
import io.ashdavies.navigation.FragmentCommand
import io.ashdavies.navigation.FragmentCommander

internal class FakeFragmentCommander : FragmentCommander {

  override val commands: MutableLiveData<LiveDataEvent<FragmentCommand>> = mutableLiveDataOf()

  override fun dispatch(command: ActivityCommand) {
    commands.value = LiveDataEvent(CompatCommand(command))
  }

  override fun dispatch(command: FragmentCommand) {
    commands.value = LiveDataEvent(command)
  }
}
