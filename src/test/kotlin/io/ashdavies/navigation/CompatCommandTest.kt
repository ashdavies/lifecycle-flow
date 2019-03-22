package io.ashdavies.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.testing.FakeActivityCommand
import io.ashdavies.testing.InstantTaskExecutorExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class CompatCommandTest {

  private val activity: FragmentActivity = mock()
  private val fragment: Fragment = mock()

  @BeforeEach
  fun setUp() {
    given(fragment.requireActivity()).willReturn(activity)
  }

  @Test
  fun `should dispatch command to activity`() {
    val command: FragmentCommand = CompatCommand(FakeActivityCommand())

    command(fragment)

    then(activity)
        .should()
        .finish()
  }
}
