package io.ashdavies.lifecycle

import io.ashdavies.architecture.StateMachine
import io.ashdavies.architecture.StateMachinery
import io.ashdavies.coroutines.ScopedViewModel
import io.ashdavies.navigation.ActivityCommander
import io.ashdavies.navigation.DispatchingCommander

abstract class ActivityViewModel<T>(initial: T) :
    ActivityCommander by DispatchingCommander(),
    StateMachine<T> by StateMachinery(initial),
    ScopedViewModel()
