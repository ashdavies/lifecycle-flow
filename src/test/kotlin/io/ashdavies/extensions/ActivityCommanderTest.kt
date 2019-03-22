package io.ashdavies.extensions

import androidx.core.app.ComponentActivity
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.LifecycleRegistry
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.testing.FakeActivityCommand
import io.ashdavies.testing.FakeActivityCommander
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class ActivityCommanderTest {

  private val activity: ComponentActivity = mock()

  private val commander = FakeActivityCommander()
  private val registry = LifecycleRegistry(activity)

  @BeforeEach
  fun setUp() {
    given(activity.lifecycle).willReturn(registry)
  }

  @Test
  fun `should observe commands`() {
    commander.observeCommands(activity)

    registry.handleLifecycleEvent(ON_START)
    commander.dispatch(FakeActivityCommand())

    then(activity)
        .should()
        .finish()
  }
}
