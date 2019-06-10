package io.ashdavies.operator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.ashdavies.extensions.mutableLiveData
import io.ashdavies.lifecycle.LiveDataScope
import io.ashdavies.lifecycle.MutableLiveDataScope
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.extensions.expect
import io.ashdavies.testing.extensions.test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class DistinctOperatorTest {

  @Test
  fun `should filter non distinct elements`() {
    val source: MutableLiveData<Int> = mutableLiveData()
    val scope: LiveDataScope<Int> = MutableLiveDataScope(source)
    val observer: Observer<Int> = source.test()
    val operator = DistinctOperator<Int>()

    operator(scope, 1)
    operator(scope, 1)
    operator(scope, 2)

    observer.expect(1, 2)
  }
}
