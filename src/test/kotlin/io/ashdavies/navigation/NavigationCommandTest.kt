package io.ashdavies.navigation

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.lifecycle.R
import io.ashdavies.testing.FakeNavigationCommand
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class NavigationCommandTest {

  private val controller: NavController = mock()
  private val fragment: Fragment = mock()
  private val view: View = mock()

  @BeforeEach
  fun setUp() {
    given(fragment.view).willReturn(view)
    given(view.getTag(R.id.nav_controller_view_tag)).willReturn(controller)
  }

  @Test
  fun `should invoke navigation command`() {
    val command: FragmentCommand = FakeNavigationCommand()

    command(fragment)

    then(controller)
        .should()
        .navigate(-1)
  }
}
