package io.ashdavies.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.ashdavies.lifecycle.Event
import io.ashdavies.navigation.ActivityCommand
import io.ashdavies.navigation.CompatCommand
import io.ashdavies.navigation.FragmentCommand
import io.ashdavies.navigation.FragmentCommander

fun FragmentCommander.dispatch(command: ActivityCommand) = dispatch(CompatCommand(command))

fun FragmentCommander.observe(owner: LifecycleOwner, observer: Observer<Event<FragmentCommand>>) = commands.observe(owner, observer)
