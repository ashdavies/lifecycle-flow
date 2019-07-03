package io.ashdavies.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.annotation.ExperimentalLifecycleApi
import io.ashdavies.architecture.Action
import io.ashdavies.extensions.mutableLiveData
import io.ashdavies.extensions.requireValue

@ExperimentalLifecycleApi
class StateMachinery<T>(initial: T) : StateMachine<T> {

  private val _state: MutableLiveData<Result<T>> = mutableLiveData()
  override val state: LiveData<Result<T>> = _state

  private val _loading: MutableLiveData<Boolean> = mutableLiveData(false)
  override val loading: LiveData<Boolean> = _loading

  init {
    _state.value = Result.success(initial)
  }

  override suspend fun action(action: Action<T>) = runLoading {
    runCatching {
      action(_state.requireValue())
    }
  }

  private inline fun <S> S.runLoading(block: S.() -> Result<T>) {
    _loading.value = true
    _state.value = block()
    _loading.value = false
  }
}
