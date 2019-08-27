package io.ashdavies.state

import io.ashdavies.architecture.Signal
import io.ashdavies.architecture.State

interface StateReducer<S : State, T : Signal> : (S, T) -> S
