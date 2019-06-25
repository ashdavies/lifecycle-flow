package io.ashdavies.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

fun Fragment.requireView(): View = view ?: throw IllegalStateException("Fragment $this has no layout")

inline fun <reified T : ViewModel> Fragment.viewModels(
    noinline owner: () -> ViewModelStoreOwner = { this },
    noinline factory: (() -> ViewModelProvider.Factory)? = null
): Lazy<T> = viewModelLazy(
    kls = T::class.java,
    store = { owner().viewModelStore },
    factory = factory
)
