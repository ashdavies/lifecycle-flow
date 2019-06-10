package io.ashdavies.testing.extensions

import io.ashdavies.lifecycle.LiveDataScope

internal class TestLiveDataScope<T> : LiveDataScope<T> {

  private val history: MutableList<T> = mutableListOf()

  override fun emit(vararg values: T) {
    history += values
  }
}
