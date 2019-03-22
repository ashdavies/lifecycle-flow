package io.ashdavies.testing

import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.LiveDataEvent
import io.ashdavies.navigation.ActivityCommand
import io.ashdavies.navigation.ActivityCommander

internal class FakeActivityCommander : ActivityCommander {

  override val commands: MutableLiveData<LiveDataEvent<ActivityCommand>> = mutableLiveDataOf()

  override fun dispatch(command: ActivityCommand) {
    commands.value = LiveDataEvent(command)
  }
}
