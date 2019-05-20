package io.ashdavies.extensions

import androidx.lifecycle.LiveData
import io.ashdavies.lifecycle.FilterOperator
import io.ashdavies.lifecycle.MapInstanceOperator
import io.ashdavies.lifecycle.MapNotNullOperator
import io.ashdavies.lifecycle.MapOperator

inline fun <reified T> LiveData<Any>.filterIsInstance(): LiveData<T> = filterIsInstance(T::class.java)

fun <T> LiveData<Any>.filterIsInstance(kls: Class<T>): LiveData<T> = mediatorLiveData(this, MapInstanceOperator(kls))

fun <T> LiveData<T?>.filterNotNull(): LiveData<T> = mediatorLiveData(this, MapNotNullOperator())

fun <T> LiveData<T>.filter(predicate: (T) -> Boolean): LiveData<T> = mediatorLiveData(this, FilterOperator(predicate))

fun <T, R> LiveData<T>.map(mapper: (T) -> R): LiveData<R> = mediatorLiveData(this, MapOperator(mapper))

fun <T> LiveData<T>.requireValue(): T = value ?: throw IllegalStateException("LiveData $this not does not contain a value")
