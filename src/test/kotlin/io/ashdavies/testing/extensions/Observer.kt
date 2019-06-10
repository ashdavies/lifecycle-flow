package io.ashdavies.testing.extensions

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.inOrder
import org.mockito.InOrder

internal fun <T> Observer<T>.expect(vararg values: T) {
  val order: InOrder = inOrder(this)
  for (value: T in values) order
      .verify(this)
      .onChanged(value)
}
