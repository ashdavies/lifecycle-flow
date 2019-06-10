package io.ashdavies.extensions

import com.google.common.truth.Truth.assertThat
import io.ashdavies.architecture.Event
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.util.concurrent.atomic.AtomicBoolean

internal class EventTest {

  private val event: Event<String> = Event("Ok")

  @Test
  fun `should fold on content`() {
    val folded: String? = event.fold({ it }, { null })

    assertThat(folded).isEqualTo("Ok")
  }

  @Test
  fun `should fold on handled`() {
    val content: String? = event.content
    val folded: String? = event.fold({ it }, { null })

    assertThat(content).isEqualTo("Ok")
    assertThat(folded).isNull()
  }

  @Test
  fun `should invoke only on content`() {
    val finished = AtomicBoolean(false)

    event.onHandled { fail("Event handled") }
    event.onContent { finished.set(true) }

    assertThat(finished.get()).isTrue()
  }

  @Test
  fun `should invoke only on handled`() {
    val finished = AtomicBoolean(false)
    val content: String? = event.content

    event.onHandled { finished.set(true) }
    event.onContent { fail("Event not handled") }

    assertThat(content).isEqualTo("Ok")
    assertThat(finished.get()).isTrue()
  }
}
