package io.ashdavies.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.ashdavies.lifecycle.FilterOperator
import io.ashdavies.lifecycle.MapOperator
import io.ashdavies.lifecycle.Operator

@Suppress("UNCHECKED_CAST")
inline fun <reified T> LiveData<*>.filterIsInstance(): LiveData<T> = filter { it is T } as LiveData<T>

@Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")
inline fun <T> LiveData<T?>.filterNotNull(): LiveData<T> = filter { it != null } as LiveData<T>

fun <T> LiveData<T>.filter(predicate: (T) -> Boolean): LiveData<T> = transform(FilterOperator(predicate))

fun <T, R> LiveData<T>.map(mapper: (T) -> R): LiveData<R> = transform(MapOperator(mapper))

fun <T> LiveData<T>.requireValue(): T = value ?: throw IllegalStateException("LiveData $this not does not contain a value")

private fun <T, R> LiveData<T>.transform(operator: Operator<T, R>): MediatorLiveData<R> {
  val output = MediatorLiveData<R>()
  output.addSource(this) {
    operator(output, it)
  }
  return output
}

