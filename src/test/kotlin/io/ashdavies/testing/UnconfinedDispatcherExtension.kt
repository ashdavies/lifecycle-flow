package io.ashdavies.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

internal class UnconfinedDispatcherExtension : BeforeEachCallback, AfterEachCallback {

  override fun beforeEach(context: ExtensionContext) {
    Dispatchers.setMain(Unconfined)
  }

  override fun afterEach(context: ExtensionContext) {
    Dispatchers.resetMain()
  }
}
