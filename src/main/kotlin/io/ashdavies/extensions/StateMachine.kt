package io.ashdavies.extensions

import io.ashdavies.architecture.StateMachine
import kotlinx.coroutines.runBlocking

fun <T> StateMachine<T>.action(block: (Result<T>) -> T) {
  runBlocking {
    action {
      block(it)
    }
  }
}
