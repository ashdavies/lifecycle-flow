package io.ashdavies.lifecycle

import androidx.core.app.ComponentActivity
import io.ashdavies.navigation.ActivityCommand

internal class ActivityObserver(private val activity: ComponentActivity) : EventObserver<ActivityCommand>() {

  override fun onValue(it: ActivityCommand) = it(activity)
}
