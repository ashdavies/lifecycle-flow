package io.ashdavies.state

import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Action
import io.ashdavies.lifecycle.jupiter.InstantTaskExecutorExtension
import io.ashdavies.lifecycle.testing.TestObserver
import io.ashdavies.lifecycle.testing.test
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.LazyThreadSafetyMode.NONE

@ExperimentalLifecycleApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class StateMachineryTest {

  private val machine: StateMachine<State> by lazy<StateMachine<State>>(NONE) { StateMachinery(State.Started) }

  private val state: TestObserver<Result<State>>
    get() = machine
        .state
        .test()

  private val loading: TestObserver<Boolean>
    get() = machine
        .loading
        .test()

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
    val state: TestObserver<Result<State>> = state

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
    val state: TestObserver<Result<State>> = state

    machine.action {
      throw exception
    }

    state.expect(
        Result.success(State.Started),
        Result.failure(exception)
    )
  }

  @Test
  fun `should emit loading state following action`() = runBlocking {
    val loading: TestObserver<Boolean> = loading

    machine.action { State.Finished }

    loading.expect(false, true, false)
  }

  private sealed class State {

    object Started : State()
    object Finished : State()
  }

  private suspend fun <T> StateMachine<T>.action(block: (Result<T>) -> T) = action(object : Action<T> {
    override suspend fun invoke(result: Result<T>): T = block(result)
  })
}
