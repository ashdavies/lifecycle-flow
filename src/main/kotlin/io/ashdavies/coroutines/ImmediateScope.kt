package io.ashdavies.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

internal object ImmediateScope : CoroutineScope {

  @ExperimentalCoroutinesApi
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main.immediate
}
