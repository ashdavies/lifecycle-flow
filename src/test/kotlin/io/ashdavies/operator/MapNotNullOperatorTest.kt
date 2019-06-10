package io.ashdavies.operator

import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLiveDataScope
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapNotNullOperatorTest {

  private val operator = MapNotNullOperator<Int>()
  private val scope = TestLiveDataScope<Int>()

  @Test
  fun `should map value`() = runBlocking<Unit> {
    operator(scope, 42)

    scope.expect(42)
  }

  @Test
  @Disabled
  fun `should not map null value`() = runBlocking<Unit> {
    operator(scope, null)

    scope.expect()
  }
}
