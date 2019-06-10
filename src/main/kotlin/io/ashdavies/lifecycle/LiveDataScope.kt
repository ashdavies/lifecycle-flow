package io.ashdavies.lifecycle

interface LiveDataScope<T> {

  fun emit(vararg values: T)
}
