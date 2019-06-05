package io.ashdavies.operator

import io.ashdavies.lifecycle.LiveDataScope

internal class DistinctOperator<T> : Operator<T, T> {

  private var last: T? = null

  override fun invoke(scope: LiveDataScope<T>, value: T) {
    if (value != last) {
      scope.emit(value)
      last = value
    }
  }
}
