package org.markensic.learn.jetpack.lifecycle

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

internal class ActivityLifecycleOwner(private val c: Context, private val lifecycle: Lifecycle): LifecycleOwner {

  override fun getLifecycle() = lifecycle
}