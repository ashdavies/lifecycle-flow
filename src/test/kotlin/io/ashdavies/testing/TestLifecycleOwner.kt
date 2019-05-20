package io.ashdavies.testing

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_CREATE
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.INITIALIZED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

internal class TestLifecycleOwner(state: State = INITIALIZED) : LifecycleOwner {

  private val registry = LifecycleRegistry(this)

  init {
    registry.markState(state)
  }

  override fun getLifecycle(): Lifecycle = registry

  fun onCreate() = registry.handleLifecycleEvent(ON_CREATE)

  fun onStart() = registry.handleLifecycleEvent(ON_START)

  fun onResume() = registry.handleLifecycleEvent(ON_RESUME)

  fun onPause() = registry.handleLifecycleEvent(ON_PAUSE)

  fun onStop() = registry.handleLifecycleEvent(ON_STOP)

  fun onDestroy() = registry.handleLifecycleEvent(ON_DESTROY)
}
