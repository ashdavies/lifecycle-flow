package io.ashdavies.extensions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.function.Executable

internal inline fun <reified T : Throwable> assertThrows(noinline executable: () -> Unit): T = Assertions.assertThrows(T::class.java, Executable(executable))
