package io.ashdavies.architecture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveDataOf
import io.ashdavies.extensions.requireValue

class StateMachinery<T>(initial: T) : StateMachine<T> {

  private val _state: MutableLiveData<Result<T>> = mutableLiveDataOf()
  override val state: LiveData<Result<T>> = _state

  private val _loading: MutableLiveData<Boolean> = mutableLiveDataOf()
  override val loading: LiveData<Boolean> = _loading

  init {
    _state.value = Result.success(initial)
  }

  override suspend fun action(block: suspend (Result<T>) -> T) = runLoading {
    runCatching {
      block(_state.requireValue())
    }
  }

  private suspend fun runLoading(block: suspend () -> Result<T>) {
    _loading.value = true
    _state.value = block()
    _loading.value = false
  }
}
