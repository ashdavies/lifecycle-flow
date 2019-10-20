package io.ashdavies.extensions

import android.view.View
import androidx.fragment.app.Fragment

fun Fragment.requireView(): View = view ?: throw IllegalStateException("Fragment $this has no layout")
