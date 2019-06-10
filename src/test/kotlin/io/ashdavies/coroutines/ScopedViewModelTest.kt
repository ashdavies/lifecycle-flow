package io.ashdavies.coroutines

import io.ashdavies.testing.UnconfinedDispatcherExtension
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(UnconfinedDispatcherExtension::class)
internal class ScopedViewModelTest {

  private val model = TestViewModel()

  @Test
  fun `should cancel job on view model clear`() {
    val deferred: Deferred<Unit> = model.async {
      delay(100)
      Unit
    }

    model.onCleared()

    assertThrows<CancellationException> {
      runBlocking {
        deferred.await()
      }
    }
  }

  class TestViewModel : ScopedViewModel() {

    public override fun onCleared() = super.onCleared()
  }
}
