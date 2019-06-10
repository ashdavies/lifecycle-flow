package io.ashdavies.operator

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.extensions.assertTimeout
import io.ashdavies.lifecycle.LiveDataScope
import io.ashdavies.lifecycle.MutableLiveDataScope
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.extensions.await
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapNotNullOperatorTest {

  private val operator = MapNotNullOperator<Int>()
  private val output = MutableLiveData<Int>()

  @Test
  fun `should map value`() = runBlocking<Unit> {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)

    operator(scope, 42)

    assertThat(output.await()).isEqualTo(42)
  }

  @Test
  @Disabled
  fun `should not map null value`() = runBlocking<Unit> {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)

    operator(scope, null)

    assertTimeout(10) {
      output.await()
    }
  }
}
