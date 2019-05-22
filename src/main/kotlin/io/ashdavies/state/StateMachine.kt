package io.ashdavies.state

import io.ashdavies.annotation.ExperimentalLifecycleApi

@ExperimentalLifecycleApi
interface StateMachine<T> : StateExposition<T> {

  suspend fun action(block: suspend (Result<T>) -> T)
}
