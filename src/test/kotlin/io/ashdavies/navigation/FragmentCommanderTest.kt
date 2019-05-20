package io.ashdavies.navigation

import androidx.fragment.app.Fragment
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.mock
import io.ashdavies.extensions.await
import io.ashdavies.lifecycle.Event
import io.ashdavies.testing.FakeFragmentCommand
import io.ashdavies.testing.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class FragmentCommanderTest {

  private val commander: FragmentCommander = DispatchingCommander()
  private val command: FragmentCommand = FakeFragmentCommand(mock())

  @Test
  fun `should dispatch command`() = runBlocking<Unit> {
    commander.dispatch(command)

    val actual: Event<LifecycleCommand<Fragment>> = commander
        .commands
        .await()

    assertThat(actual).isEqualTo(Event(command))
  }
}
