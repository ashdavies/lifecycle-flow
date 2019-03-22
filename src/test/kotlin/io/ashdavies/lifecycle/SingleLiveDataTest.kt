package io.ashdavies.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.isA
import com.nhaarman.mockito_kotlin.then
import com.nhaarman.mockito_kotlin.times
import io.ashdavies.extensions.assertThrows
import io.ashdavies.extensions.test
import io.ashdavies.navigation.ActivityCommand
import io.ashdavies.testing.FakeActivityCommand
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.TestLifecycleOwner
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
internal class SingleLiveDataTest {

  private val command: ActivityCommand = FakeActivityCommand()
  private val source: MutableLiveData<ActivityCommand> = SingleLiveData()
  private val owner = TestLifecycleOwner()

  @BeforeEach
  fun setUp() {
    source.value = command
  }

  @Test
  fun `should notify new live data value`() {
    val observer: Observer<ActivityCommand> = source.test()
    owner.onStart()

    then(observer)
        .should()
        .onChanged(command)

    source.value = FakeActivityCommand()

    then(observer)
        .should(times(2))
        .onChanged(isA<FakeActivityCommand>())
  }

  @Test
  fun `should consume live data value`() {
    val observer: Observer<ActivityCommand> = source.test()
    owner.onStart()

    then(observer)
        .should()
        .onChanged(command)

    owner.onStop()
    owner.onStart()

    then(observer)
        .should()
        .onChanged(command)
  }

  @Test
  fun `should throw exceptions for multiple observers`() {
    owner.onStart()

    source.observe(owner, Observer { })

    assertThrows<IllegalStateException> {
      source.observe(owner, Observer { })
    }
  }
}
