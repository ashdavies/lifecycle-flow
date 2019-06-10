package io.ashdavies.coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class ScopedViewModelTest {

  private val model = TestViewModel()

  @BeforeEach
  fun setUp() {
    Dispatchers.setMain(Unconfined)
  }

  @AfterEach
  fun tearDown() {
    Dispatchers.resetMain()
  }

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
