package io.ashdavies.lifecycle

internal class MapNotNullOperator<T : Any?> : Operator<T?, T> {

  override fun invoke(scope: LiveDataScope<T>, value: T?) {
    if (value != null) {
      scope.emit(value)
    }
  }
}
