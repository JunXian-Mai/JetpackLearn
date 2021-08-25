package org.markensic.learn.jetpack

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import org.markensic.learn.jetpack.databinding.ActivityBindingDataBinding
import org.markensic.learn.jetpack.viewmodels.DataBinding
import org.markensic.learn.jetpack.viewmodels.DataBindingViewModel
import org.markensic.learn.jetpack.viewmodels.DataBindingViewModelLiveData

class DataBindingActivity : BaseActicity(), LifecycleObserver {

  private lateinit var binding: ActivityBindingDataBinding
  private val liveData: DataBindingViewModelLiveData by viewModels()
  private val viewModel: DataBindingViewModel by viewModels()
  private val dataBinding: DataBinding = DataBinding()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_data)

    binding.liveData = liveData
//    binding.viewModel = viewModel
//    binding.dataBinding = dataBinding
//    lifecycle.addObserver(this)

    //双向绑定 1： 配合"@={xxxx}"
    binding.lifecycleOwner = this

    //双向绑定 2： 配合"@={xxxx}"
//    liveData.detail.observe(this, { t ->
//      t?.let {
//        if (it.isNotBlank()) {
//          if (binding.tv.text.toString() != it) {
//            binding.tv.text = it
//          }
//        }
//      }
//    })

//    liveData.testText.observe(this) {
//      logd("changed!")
//    }
  }
}
