package io.ashdavies.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import io.ashdavies.properties.ViewModelLazy

fun <T : ViewModel> viewModelLazy(
    kls: Class<T>,
    store: () -> ViewModelStore,
    factory: (() -> ViewModelProvider.Factory)? = null
): Lazy<T> = ViewModelLazy(
    kls = kls,
    owner = store,
    factory = factory ?: { ViewModelProvider.NewInstanceFactory() }
)
