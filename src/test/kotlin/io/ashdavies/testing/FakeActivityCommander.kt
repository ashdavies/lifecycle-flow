package io.ashdavies.testing

import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.Event
import io.ashdavies.navigation.ActivityCommand
import io.ashdavies.navigation.ActivityCommander

internal class FakeActivityCommander : ActivityCommander {

  override val events: MutableLiveData<Event<ActivityCommand>> = mutableLiveDataOf()

  override fun dispatch(command: ActivityCommand) {
    events.value = Event(command)
  }
}
