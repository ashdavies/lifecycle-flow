package io.ashdavies.state

typealias StateReducer<S, T> = (state: S, signal: T) -> S
