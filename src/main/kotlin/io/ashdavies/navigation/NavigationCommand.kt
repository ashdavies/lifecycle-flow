package io.ashdavies.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.ashdavies.extensions.requireView

abstract class NavigationCommand : FragmentCommand {

  private val Fragment.controller: NavController
    get() = Navigation.findNavController(requireView())

  override fun invoke(fragment: Fragment) = invoke(fragment.controller)

  abstract fun invoke(controller: NavController)
}
