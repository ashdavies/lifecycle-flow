package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class FilterOperatorTest {

  private val output = MediatorLiveData<Int>()

  @Test
  fun `should filter value`() {
    val operator: Operator<Int, Int> = FilterOperator { false }

    operator(output, 42)

    assertThat(output.value).isNull()
  }

  @Test
  fun `should not filter value`() {
    val operator: Operator<Int, Int> = FilterOperator { true }

    operator(output, 42)

    assertThat(output.value).isEqualTo(42)
  }
}
