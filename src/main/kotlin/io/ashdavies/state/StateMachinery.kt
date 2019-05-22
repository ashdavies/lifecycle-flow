package io.ashdavies.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveData
import io.ashdavies.extensions.requireValue

class StateMachinery<T>(initial: T) : StateMachine<T> {

  private val _state: MutableLiveData<Result<T>> = mutableLiveData()
  override val state: LiveData<Result<T>> = _state

  private val _loading: MutableLiveData<Boolean> = mutableLiveData()
  override val loading: LiveData<Boolean> = _loading

  init {
    _state.value = Result.success(initial)
  }

  fun action(block: (Result<T>) -> Result<T>) {
    runLoading {
      block(_state.requireValue())
    }
  }

  override suspend fun action(block: suspend (Result<T>) -> T) = runLoading {
    runCatching {
      block(_state.requireValue())
    }
  }

  private inline fun <S> S.runLoading(block: S.() -> Result<T>) {
    _loading.value = true
    _state.value = block()
    _loading.value = false
  }
}