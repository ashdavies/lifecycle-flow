package io.ashdavies.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.lifecycle.testing.InstantTaskExecutorExtension
import io.ashdavies.lifecycle.testing.TestObserver
import io.ashdavies.lifecycle.testing.test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class LiveDataTest {

  private val source: MutableLiveData<Int> = mutableLiveData()

  @Test
  fun `should filter distinct`() {
    val distinct: LiveData<Int> = source.distinctUntilChanged()
    val observer: TestObserver<Int> = distinct.test()

    source.emit(1, 1, 2, 2, 3, 3, 3)

    observer.expect(1, 2, 3)
  }

  @Test
  fun `should filter instance`() {
    val source: MutableLiveData<Animal> = mutableLiveData()
    val filtered: LiveData<Animal.Cat> = source.filterIsInstance()
    val observer: TestObserver<Animal.Cat> = filtered.test()

    source {
      emit(Animal.Dog, Animal.Cat)
    }

    observer.expect(Animal.Cat)
  }

  @Test
  fun `should filter not null`() {
    val source: MutableLiveData<Int?> = mutableLiveData()
    val notNull: LiveData<Int> = source.filterNotNull()
    val observer: TestObserver<Int> = notNull.test()

    source.emit(1, null, 3)

    observer.expect(1, 3)
  }

  @Test
  fun `should filter`() {
    val filtered: LiveData<Int> = source.filter { it > 3 }
    val observer: TestObserver<Int> = filtered.test()

    source.emit(1, 3, 5)

    observer.expect(5)
  }

  @Test
  fun `should return live data`() {
    val result: LiveData<String> = liveData("Hello World")
    val observer: TestObserver<String> = result.test()

    observer.expect("Hello World")
  }

  @Test
  fun `should return live data from scope`() {
    val result: LiveData<String> = liveData { emit("Hello World") }
    val observer: TestObserver<String> = result.test()

    observer.expect("Hello World")
  }

  @Test
  fun `should map values`() {
    val mapped: LiveData<Int> = source.map { it * 3 }
    val observer: TestObserver<Int> = mapped.test()

    source.emit(1, 2, 3)

    observer.expect(3, 6, 9)
  }

  @Test
  fun `should switch map values`() {
    val mapped: LiveData<Int> = source.switchMap { liveData(it + 3) }
    val observer: TestObserver<Int> = mapped.test()

    source.emit(1, 2, 3)

    observer.expect(4, 5, 6)
  }

  @Test
  fun `should require value`() {
    source.emit(42)

    assertThat(source.requireValue()).isEqualTo(42)
  }

  @Test
  fun `should throw on empty value`() {
    val exception: IllegalStateException = assertThrows {
      source.requireValue()
    }

    assertThat(exception.message).isEqualTo("LiveData $source does not contain a value")
  }

  sealed class Animal {

    object Cat : Animal()
    object Dog : Animal()
  }
}
