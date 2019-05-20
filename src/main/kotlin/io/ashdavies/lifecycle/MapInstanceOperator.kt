package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData

internal class MapInstanceOperator<T>(private val kls: Class<T>) : Operator<Any, T> {

  @Suppress("UNCHECKED_CAST")
  override fun invoke(source: MediatorLiveData<T>, value: Any) {
    if (kls.isInstance(value)) {
      source.value = value as T
    }
  }
}
