package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData

internal class MapInstanceOperator<T, R>(private val kls: Class<R>) : Operator<T, R> {

  @Suppress("UNCHECKED_CAST")
  override fun invoke(source: MediatorLiveData<R>, value: T) {
    if (kls.isInstance(value)) {
      source.value = value as R
    }
  }
}
