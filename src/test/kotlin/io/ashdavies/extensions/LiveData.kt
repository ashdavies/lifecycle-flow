package io.ashdavies.extensions

import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.ashdavies.testing.TestLifecycleOwner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
internal suspend inline fun <T> LiveData<T>.await(state: State = STARTED): T = await(TestLifecycleOwner(state))

@ExperimentalCoroutinesApi
internal suspend inline fun <T> LiveData<T>.await(owner: LifecycleOwner): T {
  val channel: ReceiveChannel<T> = openSubscription(owner)
  val value: T = channel.receive()
  channel.cancel()
  return value
}

@ExperimentalCoroutinesApi
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
