package io.ashdavies.state

import io.ashdavies.annotation.ExperimentalLifecycleApi
import kotlinx.coroutines.flow.Flow

@ExperimentalLifecycleApi
interface StateExposition<T> {

  val state: Flow<Result<T>>

  val loading: Flow<Boolean>
}
