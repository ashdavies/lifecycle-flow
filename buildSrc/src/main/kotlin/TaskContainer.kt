import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.withType

inline fun <reified T : Task> TaskContainer.configureEach(noinline block: T.() -> Unit) {
  withType<T>().configureEach(block)
}
