package io.ashdavies.extensions

import android.view.View
import androidx.fragment.app.Fragment
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test

internal class FragmentTest {

  private val fragment: Fragment = mock()
  private val view: View = mock()

  @Test
  fun `should return view`() {
    given(fragment.view).willReturn(view)

    assertThat(fragment.requireView()).isEqualTo(view)
  }

  @Test
  fun `should throw exception`() {
    val thrown: IllegalStateException = assertThrows {
      fragment.requireView()
    }

    assertThat(thrown.message).isEqualTo("Fragment $fragment has no layout")
  }
}
