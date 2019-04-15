package io.ashdavies.testing

import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.Event
import io.ashdavies.navigation.FragmentCommand
import io.ashdavies.navigation.FragmentCommander

internal class FakeFragmentCommander : FragmentCommander {

  override val events: MutableLiveData<Event<FragmentCommand>> = mutableLiveDataOf()

  override fun dispatch(command: FragmentCommand) {
    events.value = Event(command)
  }
}
