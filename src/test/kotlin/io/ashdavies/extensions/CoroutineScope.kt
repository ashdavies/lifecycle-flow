package io.ashdavies.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertThrows

internal fun CoroutineScope.assertTimeout(timeMillis: Long = 1000, block: suspend CoroutineScope.() -> Unit) {
  assertThrows<TimeoutCancellationException> {
    withTimeout(timeMillis, block)
  }
}

internal inline fun <reified T : Throwable> CoroutineScope.assertThrows(noinline block: suspend CoroutineScope.() -> Unit): T = assertThrows(T::class.java) {
  runBlocking(coroutineContext, block)
}
