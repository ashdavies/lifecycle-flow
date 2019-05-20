package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapInstanceOperatorTest {

  private val operator: Operator<Any, String> = MapInstanceOperator(String::class.java)

  private val output = MediatorLiveData<String>()

  @Test
  fun `should map string value`() {
    operator(output, "42")

    assertThat(output.value).isEqualTo("42")
  }

  @Test
  fun `should not map int value`() {
    operator(output, 42)

    assertThat(output.value).isNull()
  }
}
