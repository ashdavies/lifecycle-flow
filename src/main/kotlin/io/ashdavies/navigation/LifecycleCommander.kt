package io.ashdavies.navigation

import androidx.lifecycle.LifecycleOwner

interface LifecycleCommander<T : LifecycleOwner> : LifecycleExposition<T> {

  fun dispatch(command: LifecycleCommand<T>)
}
