package io.ashdavies.extensions

import androidx.lifecycle.MutableLiveData
import io.ashdavies.lifecycle.MutableLiveDataScope

internal fun <T> mutableLiveData(): MutableLiveData<T> = MutableLiveData()

internal fun <T> mutableLiveData(block: MutableLiveDataScope<T>.() -> Unit): MutableLiveData<T> {
  val output = MutableLiveData<T>()
  val scope = MutableLiveDataScope(output)

  scope.block()
  return output
}
