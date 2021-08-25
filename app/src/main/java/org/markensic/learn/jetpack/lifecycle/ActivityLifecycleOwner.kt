package org.markensic.learn.jetpack.lifecycle

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

internal class ActivityLifecycleOwner(private val c: Context, private val lifecycle: Lifecycle) :
  LifecycleOwner {

  override fun getLifecycle() = lifecycle
}
