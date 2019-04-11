package io.ashdavies.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.lifecycle.R
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.RETURNS_DEEP_STUBS

internal class NavigationCommandTest {

  private val fragment: Fragment = mock(defaultAnswer = RETURNS_DEEP_STUBS)
  private val controller: NavController = mock()

  @BeforeEach
  fun setUp() {
    given(fragment.view?.getTag(R.id.nav_controller_view_tag)).willReturn(controller)
  }

  @Test
  fun `should invoke navigation command`() {
    val command: FragmentCommand = NavigationCommand(-1)

    command(fragment)

    then(controller)
        .should()
        .navigate(-1)
  }
}
