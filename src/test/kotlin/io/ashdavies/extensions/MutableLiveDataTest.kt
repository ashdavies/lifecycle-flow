package io.ashdavies.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.extensions.expect
import io.ashdavies.testing.extensions.test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MutableLiveDataTest {

  private val source: MutableLiveData<Int> = mutableLiveData()

  @Test
  fun `should get scope from live data`() {
    val observer: Observer<Int> = source.test()

    source.emit(42)

    observer.expect(42)
  }

  @Test
  fun `should operate within live data scope`() {
    val observer: Observer<Int> = source.test()

    source.emit(42)

    observer.expect(42)
  }

  @Test
  fun `should emit sequence values`() {
    val observer: Observer<Int> = source.test()

    source.emit(1, 2, 3)

    observer.expect(1, 2, 3)
  }

  @Test
  fun `should return mutable live data`() {
    val result: MutableLiveData<String> = mutableLiveData("Hello World")

    result
        .test()
        .expect("Hello World")
  }

  @Test
  fun `should return mutable live data from scope`() {
    val result: MutableLiveData<String> = mutableLiveData { emit("Hello World") }

    result
        .test()
        .expect("Hello World")
  }
}
