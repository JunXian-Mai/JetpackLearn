package org.markensic.learn.jetpack

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

open class BaseActicity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(this::class.simpleName + "_Life", "onCreate")
  }

  override fun onStart() {
    super.onStart()
    Log.d(this::class.simpleName + "_Life", "onStart")
  }

  override fun onPause() {
    super.onPause()
    Log.d(this::class.simpleName + "_Life", "onPause")
  }

  override fun onStop() {
    super.onStop()
    Log.d(this::class.simpleName + "_Life", "onStop")
  }

  override fun onResume() {
    super.onResume()
    Log.d(this::class.simpleName + "_Life", "onResume")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(this::class.simpleName + "_Life", "onDestroy")
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    val result = super.dispatchTouchEvent(ev)
    Log.e(this::class.simpleName, "Acticity.dispatchTouchEvent")
    return false
  }
}