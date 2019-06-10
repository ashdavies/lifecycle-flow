package io.ashdavies.testing.extensions

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import io.ashdavies.testing.TestLifecycleOwner
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.ReceiveChannel

internal suspend inline fun <T> LiveData<T>.await(): T = await(TestLifecycleOwner(STARTED))

internal suspend inline fun <T> LiveData<T>.await(owner: LifecycleOwner): T {
  val channel: ReceiveChannel<T> = openSubscription(owner)
  val value: T = channel.receive()
  channel.cancel()
  return value
}

internal suspend inline fun <T> LiveData<T>.expect(vararg values: T) = expect(TestLifecycleOwner(STARTED), *values)

internal suspend inline fun <T> LiveData<T>.expect(owner: LifecycleOwner, vararg values: T) {
  val channel: ReceiveChannel<T> = openSubscription(owner)
  for (value: T in values) check(channel.receive() == value)
  check(channel.isClosedForReceive)
}

internal fun <T> LiveData<T>.openSubscription(owner: LifecycleOwner): ReceiveChannel<T> {
  val channel: Channel<T> = Channel(UNLIMITED)

  val observer = Observer<T> {
    channel.offer(it)
  }

  channel.invokeOnClose {
    removeObserver(observer)
  }

  observe(owner, observer)
  return channel
}

internal fun <T> LiveData<T>.test(state: State = STARTED): Observer<T> = test(TestLifecycleOwner(state))

internal fun <T> LiveData<T>.test(owner: LifecycleOwner): Observer<T> {
  val observer: Observer<T> = mock()
  observe(owner, observer)
  return observer
}
