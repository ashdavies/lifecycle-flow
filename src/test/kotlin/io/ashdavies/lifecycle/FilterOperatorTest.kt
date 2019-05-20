package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class FilterOperatorTest {

  private val output = MutableLiveData<Int>()

  @Test
  fun `should filter value`() {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)
    val operator: Operator<Int, Int> = FilterOperator { false }

    operator(scope, 42)

    assertThat(output.value).isNull()
  }

  @Test
  fun `should not filter value`() {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)
    val operator: Operator<Int, Int> = FilterOperator { true }

    operator(scope, 42)

    assertThat(output.value).isEqualTo(42)
  }
}
