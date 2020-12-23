package org.markensic.learn.jetpack

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.markensic.learn.jetpack.databinding.ActivityBindingViewBinding

class ViewBindingActivity : AppCompatActivity(), LifecycleObserver {
  private lateinit var binding: ActivityBindingViewBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    lifecycle.addObserver(this)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun onCreated() {
    binding = ActivityBindingViewBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btn.setOnClickListener {
      startActivity(Intent(this, DataBindingActivity::class.java))
    }
  }
}