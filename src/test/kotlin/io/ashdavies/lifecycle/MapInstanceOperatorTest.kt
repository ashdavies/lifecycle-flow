package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.extensions.assertTimeout
import io.ashdavies.extensions.await
import io.ashdavies.operator.MapInstanceOperator
import io.ashdavies.operator.Operator
import io.ashdavies.testing.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class MapInstanceOperatorTest {

  private val operator: Operator<Any, String> = MapInstanceOperator(String::class.java)

  private val output = MutableLiveData<String>()

  @Test
  fun `should map string value`() = runBlocking<Unit> {
    val scope: LiveDataScope<String> = MutableLiveDataScope(output)

    operator(scope, "42")

    assertThat(output.await()).isEqualTo("42")
  }

  @Test
  fun `should not map int value`() = runBlocking {
    val scope: LiveDataScope<String> = MutableLiveDataScope(output)

    operator(scope, 42)

    assertTimeout(10) {
      output.await()
    }
  }
}
