package org.markensic.learn.jetpack

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.markensic.learn.jetpack.databinding.ActivityScrollBinding
import org.markensic.learn.jetpack.models.lhc
import org.markensic.learn.jetpack.viewmodels.adapter.WaterfallAdapter

class ScrollActivity: BaseActicity(), LifecycleObserver {
  private lateinit var binding: ActivityScrollBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_scroll)

    binding.rcv.apply {
      layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
      adapter = WaterfallAdapter(this@ScrollActivity).apply {
        waterfallPic.addAll(lhc)
      }
    }
  }
}