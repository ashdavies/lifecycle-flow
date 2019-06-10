package io.ashdavies.extensions

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class FragmentTest {

  private val view: View = View(Activity())

  @Test
  fun `should return view`() {
    val fragment: Fragment = FakeFragment(view)

    assertThat(fragment.requireView()).isEqualTo(view)
  }

  @Test
  fun `should throw exception`() {
    val fragment: Fragment = FakeFragment(null)

    val thrown: IllegalStateException = assertThrows {
      fragment.requireView()
    }

    assertThat(thrown.message).isEqualTo("Fragment $fragment has no layout")
  }

  private class FakeActivity : Activity()

  private class FakeFragment(private val root: View?) : Fragment() {

    override fun getView(): View? = root
  }
}
