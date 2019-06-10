package io.ashdavies.signal

import androidx.lifecycle.Observer
import io.ashdavies.architecture.Event
import io.ashdavies.architecture.Signal
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.extensions.expect
import io.ashdavies.testing.extensions.test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class SignalDispatcherTest {

  private val store: SignalStore<TestSignal> = SignalDispatcher()

  @Test
  fun `should emit signal`() {
    val observer: Observer<Event<TestSignal>> = store
        .signals
        .test()

    store.signal(TestSignal)

    observer.expect(Event(TestSignal))
  }

  private object TestSignal : Signal
}
