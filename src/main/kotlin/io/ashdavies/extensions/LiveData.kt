package io.ashdavies.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.ashdavies.lifecycle.LiveDataScope
import io.ashdavies.operator.FilterOperator
import io.ashdavies.operator.MapInstanceOperator
import io.ashdavies.operator.MapNotNullOperator

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> = Transformations.distinctUntilChanged(this)

inline fun <reified T> LiveData<*>.filterIsInstance(): LiveData<T> = filterIsInstance(T::class.java)

fun <T> LiveData<*>.filterIsInstance(kls: Class<T>): LiveData<T> = mediatorLiveData(this, MapInstanceOperator(kls))

fun <T> LiveData<T?>.filterNotNull(): LiveData<T> = mediatorLiveData(this, MapNotNullOperator())

fun <T> LiveData<T>.filter(predicate: (T) -> Boolean): LiveData<T> = mediatorLiveData(this, FilterOperator(predicate))

fun <T> liveData(value: T): LiveData<T> = mutableLiveData(value)

fun <T> liveData(block: LiveDataScope<T>.() -> Unit): LiveData<T> = mutableLiveData(block)

fun <T, R> LiveData<T>.map(mapper: (T) -> R): LiveData<R> = Transformations.map(this, mapper)

fun <T, R> LiveData<T>.switchMap(mapper: (T) -> LiveData<R>): LiveData<R> = Transformations.switchMap(this, mapper)

fun <T> LiveData<T>.requireValue(): T = value ?: throw IllegalStateException("LiveData $this does not contain a value")
