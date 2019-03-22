package io.ashdavies.navigation

import androidx.fragment.app.Fragment

internal class CompatCommand(private val command: ActivityCommand) : FragmentCommand {

  override fun invoke(fragment: Fragment) = command(fragment.requireActivity())
}
