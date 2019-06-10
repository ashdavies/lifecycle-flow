package io.ashdavies.operator

import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLiveDataScope
import io.ashdavies.testing.extensions.invoke
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class DistinctOperatorTest {

  private val operator = DistinctOperator<Int>()
  private val scope = TestLiveDataScope<Int>()

  @Test
  fun `should filter non distinct elements`() {
    operator(scope, 1, 1, 2)

    scope.expect(1, 2)
  }
}
