package io.ashdavies.architecture

interface Action<T> {

  suspend operator fun invoke(result: Result<T>): T
}
