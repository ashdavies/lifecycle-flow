package io.ashdavies.operator

import io.ashdavies.lifecycle.jupiter.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLiveDataScope
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.LazyThreadSafetyMode.NONE

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapNotNullOperatorTest {

  private val scope: TestLiveDataScope<Int> by lazy(NONE) { TestLiveDataScope<Int>() }
  private val operator = MapNotNullOperator<Int>()

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
