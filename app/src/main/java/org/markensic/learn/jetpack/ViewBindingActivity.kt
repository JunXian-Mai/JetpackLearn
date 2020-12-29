package org.markensic.learn.jetpack

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.learn.jetpack.databinding.ActivityBindingViewBinding

class ViewBindingActivity : AppCompatActivity(), LifecycleObserver {
  private lateinit var binding: ActivityBindingViewBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityBindingViewBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btn.setOnClickListener {
      startActivity(Intent(this, DataBindingActivity::class.java))
    }

//    binding.scaleCircle.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
//      binding.scaleCircle.scaleCircleWithAnime(10.dp)
//    }
//
//    binding.sideCircle.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
//      binding.sideCircle.silde(PointF(120.dp, 160.dp))
//    }
//
//    binding.stringAnim.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
//      binding.stringAnim.refreshToString("桃金娘科")
//    }
//
//    val rotateAnime = ObjectAnimator.ofFloat(binding.camera, "rotatedeg", 210f).also {
//      it.startDelay = 1000
//      it.duration = 1000
//    }
//    val bottomAnime = ObjectAnimator.ofFloat(binding.camera, "bottomFilpeDeg", 210f).also {
//      it.startDelay = 200
//      it.duration = 1000
//    }
//    val headAnime = ObjectAnimator.ofFloat(binding.camera, "headFilpeDeg", 210f).also {
//      it.startDelay = 200
//      it.duration = 1000
//    }
//
//    val rotateAnime2 = ObjectAnimator.ofFloat(binding.camera, "rotatedeg", 270f).also {
//      it.startDelay = 200
//      it.duration = 1000
//    }
//
//    val animeSet = AnimatorSet()
//    animeSet.playSequentially(rotateAnime, bottomAnime, headAnime, rotateAnime2)
//    animeSet.start()

    lifecycle.addObserver(this)
  }


  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  fun onLifeResume() {

  }

}