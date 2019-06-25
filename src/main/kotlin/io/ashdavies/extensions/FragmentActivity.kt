package io.ashdavies.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.activityViewModels(): Lazy<T> = viewModels(
    owner = { requireActivity() }
)

inline fun <reified T : ViewModel> FragmentActivity.viewModels(
    noinline factory: (() -> ViewModelProvider.Factory)? = null
): Lazy<T> = viewModelLazy(
    kls = T::class.java,
    store = { viewModelStore },
    factory = factory
)
