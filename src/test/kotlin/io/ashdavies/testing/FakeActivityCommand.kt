package io.ashdavies.testing

import androidx.core.app.ComponentActivity
import io.ashdavies.navigation.ActivityCommand

internal class FakeActivityCommand : ActivityCommand {

  override fun invoke(activity: ComponentActivity) = activity.finish()
}
