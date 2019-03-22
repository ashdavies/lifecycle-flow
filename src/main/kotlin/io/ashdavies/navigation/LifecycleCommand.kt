package io.ashdavies.navigation

import androidx.lifecycle.LifecycleOwner

interface LifecycleCommand<T : LifecycleOwner> : (T) -> Unit
