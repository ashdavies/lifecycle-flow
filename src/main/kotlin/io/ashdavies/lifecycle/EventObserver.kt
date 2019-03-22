package io.ashdavies.lifecycle

import androidx.lifecycle.Observer

internal abstract class EventObserver<T> : Observer<LiveDataEvent<T>> {

  final override fun onChanged(it: LiveDataEvent<T>?) {
    val content: T? = it?.content
    if (content != null) {
      onValue(content)
    }
  }

  abstract fun onValue(it: T)
}
