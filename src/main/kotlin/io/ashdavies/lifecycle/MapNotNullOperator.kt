package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData

internal class MapNotNullOperator<T : Any?> : Operator<T?, T> {

  override fun invoke(source: MediatorLiveData<T>, value: T?) {
    if (value != null) {
      source.value = value
    }
  }
}
