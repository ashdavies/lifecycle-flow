package io.ashdavies.extensions

import android.view.View
import androidx.fragment.app.Fragment
import io.ashdavies.lifecycle.FragmentObserver
import io.ashdavies.navigation.FragmentCommander

fun Fragment.requireView(): View = view ?: throw IllegalStateException("Fragment $this has no layout")

fun Fragment.observe(commander: FragmentCommander) = commander.observe(this, FragmentObserver(this))
