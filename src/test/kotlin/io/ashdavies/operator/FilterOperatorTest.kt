package io.ashdavies.operator

import io.ashdavies.lifecycle.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLiveDataScope
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class FilterOperatorTest {

  private val scope = TestLiveDataScope<Int>()

  @Test
  fun `should filter value`() = runBlocking<Unit> {
    val operator: Operator<Int, Int> = FilterOperator { false }

    operator(scope, 42)

    scope.expect()
  }

  @Test
  fun `should not filter value`() = runBlocking<Unit> {
    val operator: Operator<Int, Int> = FilterOperator { true }

    operator(scope, 42)

    scope.expect(42)
  }
}
