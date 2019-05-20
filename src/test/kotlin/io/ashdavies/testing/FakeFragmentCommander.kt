package io.ashdavies.testing

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.lifecycle.Event
import io.ashdavies.navigation.FragmentCommand
import io.ashdavies.navigation.FragmentCommander
import io.ashdavies.navigation.LifecycleCommand

internal class FakeFragmentCommander : FragmentCommander {

  private val _commands: MutableLiveData<Event<FragmentCommand>> = mutableLiveDataOf()
  override val commands: LiveData<Event<FragmentCommand>> = _commands

  override fun dispatch(command: LifecycleCommand<Fragment>) {
    _commands.value = Event(command)
  }
}
