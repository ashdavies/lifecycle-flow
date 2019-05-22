package io.ashdavies.lifecycle

import androidx.lifecycle.Observer
import io.ashdavies.architecture.Event

interface EventObserver<T> : Observer<Event<T>> {

  override fun onChanged(it: Event<T>?) {
    val content: T? = it?.content
    if (content != null) {
      onValue(content)
    }
  }

  fun onValue(it: T)
}
