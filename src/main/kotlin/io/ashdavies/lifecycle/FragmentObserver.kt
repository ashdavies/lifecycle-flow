package io.ashdavies.lifecycle

import androidx.fragment.app.Fragment
import io.ashdavies.navigation.FragmentCommand

internal class FragmentObserver(private val fragment: Fragment) : EventObserver<FragmentCommand>() {

  override fun onValue(it: FragmentCommand) = it(fragment)
}
