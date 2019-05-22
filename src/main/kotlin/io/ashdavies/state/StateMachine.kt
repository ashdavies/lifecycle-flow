package io.ashdavies.state

interface StateMachine<T> : StateExposition<T> {

  suspend fun action(block: suspend (Result<T>) -> T)
}
