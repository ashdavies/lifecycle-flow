package io.ashdavies.extensions

import androidx.core.app.ComponentActivity
import io.ashdavies.lifecycle.ActivityObserver
import io.ashdavies.navigation.ActivityCommander

fun ActivityCommander.observeCommands(activity: ComponentActivity) = commands.observe(activity, ActivityObserver(activity))
