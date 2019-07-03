package io.ashdavies.state

import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Action

@ExperimentalLifecycleApi
interface StateMachine<T> : StateExposition<T> {

  suspend fun action(action: Action<T>)
}
