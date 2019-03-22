package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData

internal class MapOperator<T, R>(private val mapper: (T) -> R) : Operator<T, R> {

  override fun invoke(output: MediatorLiveData<R>, value: T) {
    output.value = mapper(value)
  }
}
