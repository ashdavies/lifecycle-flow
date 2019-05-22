package io.ashdavies.state

import androidx.lifecycle.LiveData

interface StateExposition<T> {

  val state: LiveData<Result<T>>

  val loading: LiveData<Boolean>
}
