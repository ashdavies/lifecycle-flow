package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData

internal class PassthroughOperator<T> : Operator<T, T> {

  override fun invoke(output: MediatorLiveData<T>, value: T) {
    output.value = value
  }
}
