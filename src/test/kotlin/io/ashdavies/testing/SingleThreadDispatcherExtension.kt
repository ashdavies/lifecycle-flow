package io.ashdavies.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

@ObsoleteCoroutinesApi
internal class SingleThreadDispatcherExtension : BeforeEachCallback, AfterEachCallback {

  private val dispatcher: ExecutorCoroutineDispatcher = newSingleThreadContext("test")

  override fun beforeEach(context: ExtensionContext) {
    Dispatchers.setMain(dispatcher)
  }

  override fun afterEach(context: ExtensionContext) {
    Dispatchers.resetMain()
    dispatcher.close()
  }
}
