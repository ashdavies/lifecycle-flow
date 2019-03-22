package io.ashdavies.lifecycle

data class LiveDataEvent<out T>(private val _content: T) {

  var handled = false
    private set

  val content: T?
    get() = if (!handled) {
      handled = true
      _content
    } else null

  fun peek(): T = _content
}
