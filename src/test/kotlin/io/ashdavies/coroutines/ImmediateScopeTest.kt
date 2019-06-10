package io.ashdavies.coroutines

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.CoroutineContext

internal class ImmediateScopeTest {

  private val finished = AtomicBoolean(false)

  @BeforeEach
  fun setUp() {
    Dispatchers.setMain(MainDispatcher(true))
  }

  @AfterEach
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `should not dispatch on main`() {
    MainScope.launch {
      finished.set(true)
    }

    assertThat(finished.get()).isFalse()
  }

  @Test
  fun `should dispatch immediately`() {
    ImmediateScope.launch {
      finished.set(true)
    }

    assertThat(finished.get()).isTrue()
  }

  private class MainDispatcher(private val deferred: Boolean) : MainCoroutineDispatcher() {

    override val immediate: MainCoroutineDispatcher by lazy { MainDispatcher(false) }

    override fun isDispatchNeeded(context: CoroutineContext): Boolean = deferred

    override fun dispatch(context: CoroutineContext, block: Runnable) {
      if (!deferred) {
        block.run()
      }
    }
  }

  private object MainScope : CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main
  }
}
