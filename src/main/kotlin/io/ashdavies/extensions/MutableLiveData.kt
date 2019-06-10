package io.ashdavies.extensions

import androidx.lifecycle.MutableLiveData
import io.ashdavies.lifecycle.MutableLiveDataScope

internal val <T> MutableLiveData<T>.scope: MutableLiveDataScope<T> get() = MutableLiveDataScope(this)

internal inline operator fun <T> MutableLiveData<T>.invoke(block: MutableLiveDataScope<T>.() -> Unit) = scope.apply(block)

internal fun <T> MutableLiveData<T>.emit(vararg values: T) = scope.emit(*values)

internal fun <T> mutableLiveData(): MutableLiveData<T> = MutableLiveData()

internal fun <T> mutableLiveData(value: T): MutableLiveData<T> = mutableLiveData { emit(value) }

internal fun <T> mutableLiveData(block: MutableLiveDataScope<T>.() -> Unit): MutableLiveData<T> {
  val output = MutableLiveData<T>()
  val scope = MutableLiveDataScope(output)

  scope.block()
  return output
}
