package io.ashdavies.operator

import io.ashdavies.lifecycle.LiveDataScope

internal interface Operator<T, R> {

  operator fun invoke(scope: LiveDataScope<R>, value: T)
}
