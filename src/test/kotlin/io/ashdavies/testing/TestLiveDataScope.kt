package io.ashdavies.testing

import io.ashdavies.lifecycle.LiveDataScope
import io.ashdavies.lifecycle.testing.LiveDataRegistry
import io.ashdavies.lifecycle.testing.TestObserver

internal class TestLiveDataScope<T> : LiveDataRegistry<T> by TestObserver(), LiveDataScope<T>
