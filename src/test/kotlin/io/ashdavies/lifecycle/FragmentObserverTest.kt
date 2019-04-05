package io.ashdavies.lifecycle

import android.content.Context
import androidx.fragment.app.Fragment
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.then
import io.ashdavies.testing.FakeFragmentCommand
import org.junit.jupiter.api.Test

internal class FragmentObserverTest {

  private val context: Context = mock()
  private val fragment: Fragment = mock()

  private val observer = FragmentObserver(fragment)

  @Test
  fun `should invoke fragment command`() {
    observer.onChanged(Event(FakeFragmentCommand(context)))

    then(fragment)
        .should()
        .onAttach(context)
  }
}
