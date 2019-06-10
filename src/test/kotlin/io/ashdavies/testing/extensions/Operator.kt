package io.ashdavies.testing.extensions

import io.ashdavies.lifecycle.LiveDataScope
import io.ashdavies.operator.Operator

internal operator fun <T, R> Operator<T, R>.invoke(scope: LiveDataScope<R>, vararg values: T) {
  for (value: T in values) {
    invoke(scope, value)
  }
}
