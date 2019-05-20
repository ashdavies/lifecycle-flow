package io.ashdavies.lifecycle

internal class MapOperator<T, R>(private val mapper: (T) -> R) : Operator<T, R> {

  override fun invoke(scope: LiveDataScope<R>, value: T) {
    scope.emit(mapper(value))
  }
}
