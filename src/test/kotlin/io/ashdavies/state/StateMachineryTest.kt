package io.ashdavies.state

import com.google.common.truth.Truth.assertThat
import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Action
import io.ashdavies.lifecycle.jupiter.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.LazyThreadSafetyMode.NONE

@FlowPreview
@ExperimentalLifecycleApi
@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class StateMachineryTest {

  private val machine: StateMachine<State> by lazy<StateMachine<State>>(NONE) { StateMachinery(State.Started) }

  @Test
  fun `should start with provided initial state`(): Unit = runBlocking {
    val actual: Result<State> = machine
        .state
        .first()

    assertThat(actual).isEqualTo(Result.success(State.Started))
  }

  @Test
  fun `should start without loading state`(): Unit = runBlocking {
    val actual: Boolean = machine
        .loading
        .first()

    assertThat(actual).isFalse()
  }

  @Test
  @Disabled
  fun `should emit state result`(): Unit = runBlocking {
    machine.action { State.Finished }

    val actual: List<Result<State>> = machine
        .state
        .take(2)
        .toList()

    assertThat(actual)
        .containsExactly(Result.success(State.Started), Result.success(State.Finished))
        .inOrder()
  }

  @Test
  @Disabled
  fun `should emit loading state during action`(): Unit = runBlocking {
    machine.action { State.Finished }

    val actual: Boolean = machine
        .loading
        .first()

    assertThat(actual).isTrue()
  }

  @Test
  @Disabled
  fun `should catch exception result`(): Unit = runBlocking {
    val exception: Exception = IllegalStateException()

    machine.action {
      throw exception
    }

    val actual: List<Boolean> = machine
        .loading
        .take(2)
        .toList()

    assertThat(actual)
        .containsExactly(Result.success(State.Started), Result.failure<State>(exception))
        .inOrder()
  }

  @Test
  @Disabled
  fun `should emit loading state following action`(): Unit = runBlocking {
    machine.action { State.Finished }

    val actual: List<Boolean> = machine
        .loading
        .take(3)
        .toList()

    assertThat(actual)
        .containsExactly(false, true, false)
        .inOrder()
  }

  private sealed class State {

    object Started : State()
    object Finished : State()
  }

  private suspend fun <T> StateMachine<T>.action(block: (Result<T>) -> T): Unit = action(object : Action<T> {
    override suspend fun invoke(result: Result<T>): T = block(result)
  })
}
