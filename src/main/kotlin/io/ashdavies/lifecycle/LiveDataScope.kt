package io.ashdavies.lifecycle

interface LiveDataScope<T> {

  fun emit(value: T)
}
