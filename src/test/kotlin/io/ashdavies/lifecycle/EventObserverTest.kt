package io.ashdavies.lifecycle

import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LiveData
import com.google.common.truth.Truth.assertThat
import io.ashdavies.architecture.Event
import io.ashdavies.extensions.liveData
import io.ashdavies.lifecycle.testing.InstantTaskExecutorExtension
import io.ashdavies.lifecycle.testing.TestLifecycleOwner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.atomic.AtomicInteger

@ExtendWith(InstantTaskExecutorExtension::class)
internal class EventObserverTest {

  @Test
  fun `should emit value only once`() {
    val source: LiveData<Event<String>> = liveData(Event("Hello World"))

    val owner = TestLifecycleOwner(STARTED)
    val emissions = AtomicInteger()

    source.observe(owner, EventObserver { emissions.incrementAndGet() })
    source.observe(owner, EventObserver { emissions.incrementAndGet() })

    assertThat(emissions.get()).isEqualTo(1)
  }
}
