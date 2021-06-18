package org.markensic.learn.jetpack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import coil.load
import org.markensic.learn.jetpack.databinding.ActivityBindingViewBinding
import org.markensic.learn.jetpack.models.lhc

class ViewBindingActivity : BaseActicity(), LifecycleObserver {
  private lateinit var binding: ActivityBindingViewBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityBindingViewBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btn.setOnClickListener {
      Log.d(this::class.simpleName + "_Life", "ViewBindingActivity -> DataBindingActivity")
      startActivity(Intent(this, ScrollActivity::class.java))
    }

    binding.iv.load(lhc.random())

//    binding.dt.setOnLongClickListener {
//      binding.dt.showDeleteImage = true
//      return@setOnLongClickListener true
//    }
//
//    binding.dt.setCancelListener { v, parent ->
//      parent.removeView(v)
//    }
//
//
//    binding.met.postDelayed(3000) {
//      binding.met.enableFloatLabel = false
//    }
//    binding.met.postDelayed(6000) {
//      binding.met.labelText = "PassWord"
//    }
//    binding.met.postDelayed(9000) {
//      binding.met.labelTextSize = 20.dp
//    }
//    binding.met.postDelayed(12000) {
//      binding.met.labelTextColor = Color.BLUE
//    }

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