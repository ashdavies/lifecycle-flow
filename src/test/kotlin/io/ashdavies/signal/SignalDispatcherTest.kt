package io.ashdavies.signal

import io.ashdavies.architecture.Event
import io.ashdavies.architecture.Signal
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.TestObserver
import io.ashdavies.testing.test
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class SignalDispatcherTest {

  private val store: SignalStore<TestSignal> = SignalDispatcher()

  @Test
  fun `should emit signal`() {
    val observer: TestObserver<Event<TestSignal>> = store
        .signals
        .test()

    store.signal(TestSignal)

    observer.expect(Event(TestSignal))
  }

  private object TestSignal : Signal
}
