package io.ashdavies.coroutines

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

abstract class SupervisorViewModel : ViewModel() {

  protected val job: Job = SupervisorJob()

  @CallSuper
  override fun onCleared() {
    super.onCleared()
    job.cancel()
  }
}
