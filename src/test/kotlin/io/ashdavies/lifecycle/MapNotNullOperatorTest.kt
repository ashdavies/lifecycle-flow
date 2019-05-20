package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapNotNullOperatorTest {

  private val operator = MapNotNullOperator<Int>()
  private val output = MutableLiveData<Int>()

  @Test
  fun `should map value`() {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)

    operator(scope, 42)

    assertThat(output.value).isEqualTo(42)
  }

  @Test
  fun `should not map null value`() {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)

    output.value = 42
    operator(scope, null)

    assertThat(output.value).isEqualTo(42)
  }
}
