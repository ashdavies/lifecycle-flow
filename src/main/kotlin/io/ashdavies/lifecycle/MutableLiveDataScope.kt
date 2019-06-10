package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData

internal class MutableLiveDataScope<T>(private val source: MutableLiveData<T>) : LiveDataScope<T> {

  override fun emit(vararg values: T) {
    for (value: T in values) {
      source.value = value
    }
  }
}
