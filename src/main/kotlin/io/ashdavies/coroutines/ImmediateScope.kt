package io.ashdavies.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal object ImmediateScope : CoroutineScope {

  override val coroutineContext: CoroutineContext = Dispatchers.Main.immediate
}
