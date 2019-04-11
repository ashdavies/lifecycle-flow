package io.ashdavies.architecture

interface StateMachine<T> : StateExposition<T> {

  suspend fun action(block: suspend (Result<T>) -> T)
}
