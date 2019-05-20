package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapNotNullOperatorTest {

  private val operator = MapNotNullOperator<Int>()
  private val output = MediatorLiveData<Int>()

  @Test
  fun `should map value`() {
    operator(output, 42)

    assertThat(output.value).isEqualTo(42)
  }

  @Test
  fun `should not map null value`() {
    output.value = 42

    operator(output, null)

    assertThat(output.value).isEqualTo(42)
  }
}
