package io.ashdavies.lifecycle

import androidx.lifecycle.Observer
import io.ashdavies.architecture.Event

internal abstract class EventObserver<T> : Observer<Event<T>> {

  final override fun onChanged(it: Event<T>?) {
    val content: T? = it?.content
    if (content != null) {
      onValue(content)
    }
  }

  abstract fun onValue(it: T)
}
