package org.markensic.learn.jetpack

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.markensic.core.global.log.CoreLog
import org.markensic.learn.jetpack.databinding.ActivityWaterfallBinding
import org.markensic.learn.jetpack.models.lhc
import org.markensic.learn.jetpack.viewmodels.WaterfallViewModel
import org.markensic.learn.jetpack.viewmodels.adapter.WaterfallAdapter

class WaterfallActivity : BaseActicity(), LifecycleObserver {

  private lateinit var binding: ActivityWaterfallBinding
  private val viewmodel: WaterfallViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_waterfall)
    binding.vm = viewmodel

    binding.rcy.apply {
      layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
      adapter = WaterfallAdapter(this@WaterfallActivity).apply {
        waterfallPic.addAll(lhc)
      }
    }

    CoreLog.d("decorView: ${window.decorView}")
  }
}
