package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class PassthroughOperatorTest {

  private val output = MediatorLiveData<Int>()
  private val operator = PassthroughOperator<Int>()

  @Test
  fun `should passthrough value`() {
    operator(output, 42)

    assertThat(output.value).isEqualTo(42)
  }
}
