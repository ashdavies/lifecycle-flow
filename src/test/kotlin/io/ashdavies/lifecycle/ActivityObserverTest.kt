package io.ashdavies.lifecycle

import androidx.core.app.ComponentActivity
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.testing.FakeActivityCommand
import org.junit.jupiter.api.Test

internal class ActivityObserverTest {

  private val activity: ComponentActivity = mock()
  private val observer = ActivityObserver(activity)

  @Test
  fun `should invoke activity command`() {
    observer.onChanged(LiveDataEvent(FakeActivityCommand()))

    then(activity)
        .should()
        .finish()
  }
}
