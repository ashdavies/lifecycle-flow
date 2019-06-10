package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.ashdavies.extensions.mutableLiveData
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.extensions.expect
import io.ashdavies.testing.extensions.test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MutableLiveDataScopeTest {

  private val source: MutableLiveData<Int> = mutableLiveData()
  private val scope: LiveDataScope<Int> = MutableLiveDataScope(source)

  @Test
  fun `should emit values`() {
    val observer: Observer<Int> = source.test()

    scope.emit(1, 2, 3)

    observer.expect(1, 2, 3)
  }
}
