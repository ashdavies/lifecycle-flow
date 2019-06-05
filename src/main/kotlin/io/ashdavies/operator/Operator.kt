package io.ashdavies.operator

import io.ashdavies.lifecycle.LiveDataScope

internal interface Operator<T, R> : (LiveDataScope<R>, T) -> Unit
