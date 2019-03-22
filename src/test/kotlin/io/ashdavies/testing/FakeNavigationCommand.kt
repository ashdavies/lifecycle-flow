package io.ashdavies.testing

import androidx.navigation.NavController
import io.ashdavies.navigation.NavigationCommand

internal class FakeNavigationCommand : NavigationCommand() {

  override fun invoke(controller: NavController) = controller.navigate(-1)
}
