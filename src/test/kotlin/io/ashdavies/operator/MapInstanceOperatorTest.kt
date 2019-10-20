package io.ashdavies.operator

import io.ashdavies.lifecycle.jupiter.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLiveDataScope
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.LazyThreadSafetyMode.NONE

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapInstanceOperatorTest {

  private val scope: TestLiveDataScope<String> by lazy(NONE) { TestLiveDataScope<String>() }
  private val operator = MapInstanceOperator<Any, String>(String::class.java)

  @Test
  fun `should map string value`() {
    operator(scope, "42")

    scope.expect("42")
  }

  @Test
  fun `should not map int value`() {
    operator(scope, 42)

    scope.expect()
  }
}
