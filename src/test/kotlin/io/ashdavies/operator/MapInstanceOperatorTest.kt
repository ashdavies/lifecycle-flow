package io.ashdavies.operator

import io.ashdavies.lifecycle.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLiveDataScope
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapInstanceOperatorTest {

  private val operator = MapInstanceOperator<Any, String>(String::class.java)
  private val scope = TestLiveDataScope<String>()

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
