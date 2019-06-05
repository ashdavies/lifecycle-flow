package io.ashdavies.extensions

import io.ashdavies.architecture.Event
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
inline fun <R, T> Event<T>.fold(
    onContent: (T) -> R,
    onHandled: () -> R
): R {
  contract {
    callsInPlace(onContent, InvocationKind.AT_MOST_ONCE)
    callsInPlace(onHandled, InvocationKind.AT_MOST_ONCE)
  }

  return when (val it: T? = content) {
    null -> onHandled()
    else -> onContent(it)
  }
}

/*@ExperimentalContracts
inline fun <T, R> Event<T>.map(block: (T) -> R): Event<R> {
  contract {
    callsInPlace(block, InvocationKind.AT_MOST_ONCE)
  }

  return when (val it: T? = content) {
    null -> Event<R>(null)
    else -> Event(block(it))
  }
}*/

@ExperimentalContracts
inline fun <T> Event<T>.onContent(block: (T) -> Unit): Event<T> {
  contract {
    callsInPlace(block, InvocationKind.AT_MOST_ONCE)
  }

  content?.apply(block)
  return this
}

@ExperimentalContracts
inline fun <T> Event<T>.onHandled(block: () -> Unit): Event<T> {
  contract {
    callsInPlace(block, InvocationKind.AT_MOST_ONCE)
  }

  if (handled) block()
  return this
}
