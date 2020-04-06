package io.ashdavies.signal

import com.google.common.truth.Truth.assertThat
import io.ashdavies.architecture.Signal
import io.ashdavies.lifecycle.jupiter.InstantTaskExecutorExtension
import io.ashdavies.signal.SignalDispatcherTest.TestSignal.Started
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@FlowPreview
@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class SignalDispatcherTest {

  private val store: SignalStore<TestSignal> = SignalDispatcher(Started)

  @Test
  fun `should emit signal`(): Unit = runBlockingTest {
    val signal: TestSignal = store
        .signals
        .first()

    assertThat(signal).isEqualTo(Started)
  }

  private sealed class TestSignal : Signal {

    object Started : TestSignal()
    object Finished : TestSignal()
  }
}
