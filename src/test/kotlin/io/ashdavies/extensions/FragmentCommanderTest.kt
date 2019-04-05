package io.ashdavies.extensions

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.testing.FakeFragmentCommand
import io.ashdavies.testing.FakeFragmentCommander
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class FragmentCommanderTest {

  private val context: Context = mock()
  private val fragment: Fragment = mock()

  private val commander = FakeFragmentCommander()
  private val registry = LifecycleRegistry(fragment)

  @BeforeEach
  fun setUp() {
    given(fragment.lifecycle).willReturn(registry)
  }

  @Test
  fun `should observe commands`() {
    fragment.observe(commander)

    registry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    commander.dispatch(FakeFragmentCommand(context))

    then(fragment)
        .should()
        .onAttach(context)
  }
}
