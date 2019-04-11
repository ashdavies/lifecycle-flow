package io.ashdavies.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import io.ashdavies.extensions.requireView

data class NavigationCommand(@IdRes private val resId: Int) : FragmentCommand {

  override fun invoke(fragment: Fragment) = Navigation
      .findNavController(fragment.requireView())
      .navigate(resId)
}
