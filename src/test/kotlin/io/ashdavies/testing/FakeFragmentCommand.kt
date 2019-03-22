package io.ashdavies.testing

import android.content.Context
import androidx.fragment.app.Fragment
import io.ashdavies.navigation.FragmentCommand

internal class FakeFragmentCommand(private val context: Context) : FragmentCommand {

  override fun invoke(fragment: Fragment) = fragment.onAttach(context)
}
