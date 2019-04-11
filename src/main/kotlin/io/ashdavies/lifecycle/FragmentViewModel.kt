package io.ashdavies.lifecycle

import io.ashdavies.architecture.StateMachine
import io.ashdavies.architecture.StateMachinery
import io.ashdavies.coroutines.ScopedViewModel
import io.ashdavies.navigation.DispatchingCommander
import io.ashdavies.navigation.FragmentCommander

abstract class FragmentViewModel<T>(initial: T) :
    FragmentCommander by DispatchingCommander(),
    StateMachine<T> by StateMachinery(initial),
    ScopedViewModel()
