package io.ashdavies.extensions

import androidx.fragment.app.FragmentActivity
import io.ashdavies.lifecycle.ActivityObserver
import io.ashdavies.navigation.ActivityCommander

fun ActivityCommander.observeCommands(activity: FragmentActivity) = commands.observe(activity, ActivityObserver(activity))
