package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.extensions.assertTimeout
import io.ashdavies.extensions.await
import io.ashdavies.operator.FilterOperator
import io.ashdavies.operator.Operator
import io.ashdavies.testing.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class FilterOperatorTest {

  private val output = MutableLiveData<Int>()

  @Test
  fun `should filter value`() = runBlocking<Unit> {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)
    val operator: Operator<Int, Int> = FilterOperator { false }

    operator(scope, 42)

    assertTimeout(10) {
      output.await()
    }
  }

  @Test
  fun `should not filter value`() = runBlocking<Unit> {
    val scope: LiveDataScope<Int> = MutableLiveDataScope(output)
    val operator: Operator<Int, Int> = FilterOperator { true }

    operator(scope, 42)

    assertThat(output.await()).isEqualTo(42)
  }
}
