package io.ashdavies.extensions

import androidx.fragment.app.Fragment
import io.ashdavies.lifecycle.FragmentObserver
import io.ashdavies.navigation.FragmentCommander

fun FragmentCommander.observeCommands(fragment: Fragment) = commands.observe(fragment, FragmentObserver(fragment))
