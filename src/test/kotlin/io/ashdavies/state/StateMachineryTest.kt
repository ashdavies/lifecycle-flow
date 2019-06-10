package io.ashdavies.state

import androidx.lifecycle.Observer
import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.testing.InstantTaskExecutorExtension
import io.ashdavies.testing.extensions.expect
import io.ashdavies.testing.extensions.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.LazyThreadSafetyMode.NONE

@ExperimentalLifecycleApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class StateMachineryTest {

  private val machine: StateMachine<State> by lazy<StateMachine<State>>(NONE) { StateMachinery(State.Started) }

  private val state: Observer<Result<State>>
    get() = machine
        .state
        .test()

  private val loading: Observer<Boolean>
    get() = machine
        .loading
        .test()

  @BeforeEach
  fun setUp() {
    Dispatchers.setMain(Unconfined)
  }

  @AfterEach
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `should start with provided initial state`() {
    state.expect(Result.success(State.Started))
  }

  @Test
  fun `should start without loading state`() {
    loading.expect(false)
  }

  @Test
  fun `should emit state result`() = runBlocking<Unit> {
    val state: Observer<Result<State>> = state

    machine.action { State.Finished }

    state.expect(
        Result.success(State.Started),
        Result.success(State.Finished)
    )
  }

  @Test
  fun `should emit loading state during action`() = runBlocking<Unit> {
    machine.action {
      loading.expect(false, true)
      State.Finished
    }

    loading.expect(false)
  }

  @Test
  fun `should catch exception result`() = runBlocking {
    val exception: Exception = IllegalStateException()
    val state: Observer<Result<State>> = state

    machine.action {
      throw exception
    }

    state.expect(Result.failure(exception))
  }

  @Test
  fun `should emit loading state following action`() = runBlocking {
    val loading: Observer<Boolean> = loading

    machine.action { State.Finished }

    loading.expect(false, true, false)
  }

  private sealed class State {

    object Started : State()
    object Finished : State()
  }
}
