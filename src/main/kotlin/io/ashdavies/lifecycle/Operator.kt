package io.ashdavies.lifecycle

internal interface Operator<T, R> : (LiveDataScope<R>, T) -> Unit
