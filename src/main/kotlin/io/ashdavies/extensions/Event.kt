package io.ashdavies.extensions

import io.ashdavies.architecture.Event
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

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

inline fun <T> Event<T>.onContent(block: (T) -> Unit): Event<T> {
  contract {
    callsInPlace(block, InvocationKind.AT_MOST_ONCE)
  }

  content?.apply(block)
  return this
}

inline fun <T> Event<T>.onHandled(block: () -> Unit): Event<T> {
  contract {
    callsInPlace(block, InvocationKind.AT_MOST_ONCE)
  }

  if (handled) block()
  return this
}
