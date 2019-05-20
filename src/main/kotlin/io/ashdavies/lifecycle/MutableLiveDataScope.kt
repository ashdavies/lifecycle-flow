package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData

internal class MutableLiveDataScope<T>(private val source: MutableLiveData<T>) : LiveDataScope<T> {
  override fun emit(value: T) {
    source.value = value
  }
}
