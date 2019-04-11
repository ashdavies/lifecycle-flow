package io.ashdavies.architecture

import androidx.lifecycle.LiveData

interface StateExposition<T> {

  val state: LiveData<Result<T>>

  val loading: LiveData<Boolean>
}
