package io.ashdavies.state

import androidx.lifecycle.LiveData
import io.ashdavies.annotation.ExperimentalLifecycleApi

@ExperimentalLifecycleApi
interface StateExposition<T> {

  val state: LiveData<Result<T>>

  val loading: LiveData<Boolean>
}
