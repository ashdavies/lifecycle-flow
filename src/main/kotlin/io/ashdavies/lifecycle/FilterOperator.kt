package io.ashdavies.lifecycle

internal class FilterOperator<T>(private val predicate: (T) -> Boolean) : Operator<T, T> {

  override fun invoke(scope: LiveDataScope<T>, value: T) {
    if (predicate(value)) {
      scope.emit(value)
    }
  }
}
