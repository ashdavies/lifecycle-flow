package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapOperatorTest {

  private val output = MediatorLiveData<String>()

  @Test
  fun `should map value`() {
    val operator: Operator<Int, String> = MapOperator(Int::toString)

    operator(output, 42)

    assertThat(output.value).isEqualTo("42")
  }
}
