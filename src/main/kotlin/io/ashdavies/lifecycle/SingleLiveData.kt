package io.ashdavies.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.ashdavies.navigation.LifecycleCommand
import java.util.concurrent.atomic.AtomicBoolean

@Deprecated(
    message = "Use LiveDataEvent instead",
    replaceWith = ReplaceWith(
        expression = "MutableLiveData<LiveDataEvent<T>>()",
        imports = [
          "androidx.lifecycle.MutableLiveData",
          "io.ashdavies.lifecycle.LiveDataEvent"
        ]
    )
)
internal class SingleLiveData<T : LifecycleCommand<*>> : MutableLiveData<T>() {

  private val pending = AtomicBoolean(false)

  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
    if (hasActiveObservers()) {
      throw IllegalStateException("Multiple observers registered but only one can be notified of changes.")
    }

    super.observe(owner, Observer<T> {
      if (pending.compareAndSet(true, false)) {
        observer.onChanged(it)
      }
    })
  }

  @MainThread
  override fun setValue(it: T?) {
    pending.set(true)
    super.setValue(it)
  }
}
