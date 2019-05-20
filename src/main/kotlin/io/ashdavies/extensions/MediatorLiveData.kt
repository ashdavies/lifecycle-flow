package io.ashdavies.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.ashdavies.lifecycle.MutableLiveDataScope
import io.ashdavies.lifecycle.Operator

internal fun <T, R> mediatorLiveData(source: LiveData<T>, operator: Operator<T, R>): MediatorLiveData<R> = mediatorLiveData {
  addSource(source) {
    operator(MutableLiveDataScope(this), it)
  }
}

internal fun <T> mediatorLiveData(block: MediatorLiveData<T>.() -> Unit): MediatorLiveData<T> {
  val mediator = MediatorLiveData<T>()
  mediator.block()
  return mediator
}

