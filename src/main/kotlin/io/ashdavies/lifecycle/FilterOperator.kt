package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData

internal class FilterOperator<T>(private val predicate: (T) -> Boolean) : Operator<T, T> {

  override fun invoke(output: MediatorLiveData<T>, value: T) {
    if (predicate(value)) {
      output.value = value
    }
  }
}
