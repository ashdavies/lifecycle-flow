package io.ashdavies.architecture

typealias Action<T> = suspend (Result<T>) -> T
