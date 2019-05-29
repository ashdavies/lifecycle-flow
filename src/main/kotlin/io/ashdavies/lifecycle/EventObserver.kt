package io.ashdavies.lifecycle

import androidx.lifecycle.Observer
import io.ashdavies.architecture.Event

class EventObserver<T>(private val block: (T) -> Unit) : Observer<Event<T>> {

  override fun onChanged(it: Event<T>) {
    val content: T? = it.content
    if (content != null) {
      block(content)
    }
  }
}
