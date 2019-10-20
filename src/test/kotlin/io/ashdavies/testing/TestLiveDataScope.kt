package io.ashdavies.testing

import androidx.lifecycle.MutableLiveData
import io.ashdavies.extensions.mutableLiveData
import io.ashdavies.lifecycle.LiveDataScope
import io.ashdavies.lifecycle.MutableLiveDataScope
import io.ashdavies.lifecycle.testing.LiveDataRegistry
import io.ashdavies.lifecycle.testing.test

internal class TestLiveDataScope<T>(
    source: MutableLiveData<T>
) : LiveDataRegistry<T> by source.test(),
    LiveDataScope<T> by MutableLiveDataScope(source) {

  constructor() : this(mutableLiveData())
}
