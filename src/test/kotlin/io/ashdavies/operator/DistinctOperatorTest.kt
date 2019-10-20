package io.ashdavies.operator

import io.ashdavies.lifecycle.jupiter.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLiveDataScope
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class DistinctOperatorTest {

  private val operator = DistinctOperator<Int>()
  private val scope = TestLiveDataScope<Int>()

  @Test
  fun `should filter non distinct elements`() {
    operator(scope, 1)
    operator(scope, 2)
    operator(scope, 3)

    scope.expect(1, 2)
  }
}
