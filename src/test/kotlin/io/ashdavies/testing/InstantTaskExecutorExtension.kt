package io.ashdavies.testing

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

internal class InstantTaskExecutorExtension : BeforeEachCallback, AfterEachCallback {

  override fun beforeEach(context: ExtensionContext) = ArchTaskExecutor
      .getInstance()
      .setDelegate(InstantTaskExecutor)

  override fun afterEach(context: ExtensionContext) = ArchTaskExecutor
      .getInstance()
      .setDelegate(null)

  private object InstantTaskExecutor : TaskExecutor() {

    override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

    override fun postToMainThread(runnable: Runnable) = runnable.run()

    override fun isMainThread(): Boolean = true
  }
}
