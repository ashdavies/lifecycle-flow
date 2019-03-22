package io.ashdavies.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock

internal fun <T> LiveData<T>.test(): Observer<T> {
  val observer: Observer<T> = mock()
  observeForever(observer)
  return observer
}
