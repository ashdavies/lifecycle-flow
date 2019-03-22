package io.ashdavies.lifecycle

import androidx.lifecycle.MediatorLiveData

internal interface Operator<T, R> : (MediatorLiveData<R>, T) -> Unit
