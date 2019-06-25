package io.ashdavies.properties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

internal class ViewModelLazy<out T : ViewModel>(
    private val kls: Class<T>,
    private val owner: () -> ViewModelStore,
    private val factory: () -> ViewModelProvider.Factory
) : Lazy<T> {

  private var cached: T? = null

  override val value: T
    get() = cached ?: ViewModelProvider(owner(), factory())
        .get(kls)
        .also { cached = it }

  override fun isInitialized(): Boolean = cached != null
}
