package io.ashdavies.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class ScopedViewModel : CoroutineScope, SupervisorViewModel() {

  override val coroutineContext: CoroutineContext = Dispatchers.Main + job
}

