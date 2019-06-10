package io.ashdavies.testing

import androidx.lifecycle.Observer

internal class TestObserver<T> : LiveDataRegistry<T>(), Observer<T> {

  override fun onChanged(it: T) = emit(it)
}
