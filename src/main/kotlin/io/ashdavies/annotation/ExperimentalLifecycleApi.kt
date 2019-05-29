package io.ashdavies.annotation

import kotlin.Experimental.Level.WARNING
import kotlin.annotation.AnnotationRetention.BINARY

@Retention(value = BINARY)
@Experimental(level = WARNING)
annotation class ExperimentalLifecycleApi
