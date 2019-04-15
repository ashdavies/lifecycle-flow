package io.ashdavies.navigation

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.extensions.test
import io.ashdavies.lifecycle.Event
import io.ashdavies.testing.FakeFragmentCommand
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class FragmentCommanderTest {

  private val commander: FragmentCommander = DispatchingCommander()
  private val command: FragmentCommand = FakeFragmentCommand(mock())

  @Test
  fun `should dispatch command`() {
    val observer: Observer<Event<FragmentCommand>> = commander
        .events
        .test()

    commander.dispatch(command)

    then(observer)
        .should()
        .onChanged(Event(command))
  }
}
