package io.ashdavies.testing

import io.ashdavies.lifecycle.LiveDataScope

internal class TestLiveDataScope<T> : LiveDataRegistry<T>(), LiveDataScope<T> {

}
