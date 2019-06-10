package io.ashdavies.operator

import io.ashdavies.lifecycle.LiveDataScope

internal class MapInstanceOperator<T : Any?, R>(private val kls: Class<R>) : Operator<T, R> {

  @Suppress("UNCHECKED_CAST")
  override fun invoke(scope: LiveDataScope<R>, value: T) {
    if (kls.isInstance(value)) {
      scope.emit(value as R)
    }
  }
}
