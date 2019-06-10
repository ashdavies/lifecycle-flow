package io.ashdavies.testing

import io.ashdavies.lifecycle.LiveDataScope

internal abstract class LiveDataRegistry<T> : LiveDataScope<T> {

  private val history: MutableList<T> = mutableListOf()

  override fun emit(vararg values: T) {
    history.addAll(values)
  }

  fun expect(vararg values: T) {
    expect(listOf(*values))
  }

  fun expect(values: List<T>) {
    check(values == history) { "Expecting values $values but it is actually $history" }
  }
}
