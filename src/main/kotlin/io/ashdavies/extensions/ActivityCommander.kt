package io.ashdavies.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import io.ashdavies.lifecycle.Event
import io.ashdavies.navigation.ActivityCommand
import io.ashdavies.navigation.ActivityCommander

fun ActivityCommander.observe(owner: LifecycleOwner, observer: Observer<Event<ActivityCommand>>) = commands.observe(owner, observer)
