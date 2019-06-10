package io.ashdavies.architecture

import com.google.common.truth.Truth.assertThat
import io.ashdavies.architecture.Event
import org.junit.jupiter.api.Test

internal class EventTest {

  private val event: Event<Int> = Event(42)

  @Test
  fun `should get content when unhandled`() {
    assertThat(event.handled).isFalse()

    assertThat(event.content).isEqualTo(42)
  }

  @Test
  fun `should mark event as handled`() {
    assertThat(event.content).isEqualTo(42)

    assertThat(event.handled).isTrue()
  }

  @Test
  fun `should get null when handled`() {
    assertThat(event.content).isEqualTo(42)

    assertThat(event.content).isNull()
  }

  @Test
  fun `should peek content`() {
    assertThat(event.peek()).isEqualTo(42)
  }
}
