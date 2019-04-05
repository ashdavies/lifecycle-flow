package io.ashdavies.extensions

import androidx.fragment.app.FragmentActivity
import io.ashdavies.lifecycle.ActivityObserver
import io.ashdavies.navigation.ActivityCommander

fun FragmentActivity.observe(commander: ActivityCommander) = commander.observe(this, ActivityObserver(this))
