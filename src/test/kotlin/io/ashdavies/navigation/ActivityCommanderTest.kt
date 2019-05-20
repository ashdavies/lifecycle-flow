package io.ashdavies.navigation

import androidx.fragment.app.FragmentActivity
import com.google.common.truth.Truth.assertThat
import io.ashdavies.extensions.await
import io.ashdavies.lifecycle.Event
import io.ashdavies.testing.FakeActivityCommand
import io.ashdavies.testing.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class ActivityCommanderTest {

  private val commander: ActivityCommander = DispatchingCommander()
  private val command: ActivityCommand = FakeActivityCommand()

  @Test
  fun `should dispatch command`() = runBlocking<Unit> {
    commander.dispatch(command)

    val actual: Event<LifecycleCommand<FragmentActivity>> = commander
        .commands
        .await()

    assertThat(actual).isEqualTo(Event(command))
  }
}
