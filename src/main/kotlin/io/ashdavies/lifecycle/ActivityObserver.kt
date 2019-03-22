package io.ashdavies.lifecycle

import androidx.fragment.app.FragmentActivity
import io.ashdavies.navigation.ActivityCommand

internal class ActivityObserver(private val activity: FragmentActivity) : EventObserver<ActivityCommand>() {

  override fun onValue(it: ActivityCommand) = it(activity)
}
