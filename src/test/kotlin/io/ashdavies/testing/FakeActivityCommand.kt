package io.ashdavies.testing

import androidx.fragment.app.FragmentActivity
import io.ashdavies.navigation.ActivityCommand

internal class FakeActivityCommand : ActivityCommand {

  override fun invoke(activity: FragmentActivity) = activity.finish()
}
