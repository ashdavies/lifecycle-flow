package io.ashdavies.testing.extensions

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import io.ashdavies.testing.TestLifecycleOwner
import io.ashdavies.testing.TestObserver

internal fun <T> LiveData<T>.test(state: State = STARTED): TestObserver<T> = test(TestLifecycleOwner(state))

internal fun <T> LiveData<T>.test(owner: LifecycleOwner): TestObserver<T> {
  val observer = TestObserver<T>()
  observe(owner, observer)
  return observer
}
