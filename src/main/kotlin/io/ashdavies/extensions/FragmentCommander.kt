package io.ashdavies.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.ashdavies.lifecycle.Event
import io.ashdavies.navigation.FragmentCommand
import io.ashdavies.navigation.FragmentCommander

fun FragmentCommander.observe(owner: LifecycleOwner, observer: Observer<Event<FragmentCommand>>) = commands.observe(owner, observer)
