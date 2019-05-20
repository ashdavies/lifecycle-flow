package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapOperatorTest {

  private val output = MutableLiveData<String>()

  @Test
  fun `should map value`() {
    val scope: LiveDataScope<String> = MutableLiveDataScope(output)
    val operator: Operator<Int, String> = MapOperator(Int::toString)

    operator(scope, 42)

    assertThat(output.value).isEqualTo("42")
  }
}
