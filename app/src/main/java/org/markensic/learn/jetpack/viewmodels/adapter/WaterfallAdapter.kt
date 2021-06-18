package org.markensic.learn.jetpack.viewmodels.adapter

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.target.ImageViewTarget
import org.markensic.baselibrary.api.utils.DisPlayUtils
import org.markensic.baselibrary.global.extensions.logd
import org.markensic.learn.jetpack.databinding.ItemWaterfallBinding
import kotlin.math.log

internal val fillWidthRatio = DisPlayUtils.heightPixels.toFloat() / DisPlayUtils.widthPixels

abstract class WaterfallAdapter(val context: Context) : RecyclerView.Adapter<WaterfallAdapter.ViewHolder>() {

  private lateinit var binding: ItemWaterfallBinding
  val waterfallPic = mutableListOf<String>()

  class ViewHolder(val binding: ItemWaterfallBinding) : RecyclerView.ViewHolder(binding.root) {

    fun loadPic(url: String, changeLayoutManager: (Boolean) -> Unit) {
      binding.iv.load(url) {
        target {
          (it as BitmapDrawable).apply {
            val ratio = bitmap.height.toFloat() / bitmap.width
            val fillWidth = ratio > fillWidthRatio
            changeLayoutManager(fillWidth)
            if (fillWidth) {
              val finalHeight = DisPlayUtils.widthPixels * bitmap.height / bitmap.width
              IvReSize(DisPlayUtils.widthPixels, finalHeight)
            } else {
              val finalHeight = DisPlayUtils.widthPixels / 2 * bitmap.height / bitmap.width
              IvReSize(DisPlayUtils.widthPixels / 2, finalHeight)
            }
          }
          binding.iv.load(it)
        }
      }
    }

    fun IvReSize(width: Int, height: Int) {
      binding.iv.layoutParams.width = width
      binding.iv.layoutParams.height = height
      binding.iv.invalidate()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    binding = ItemWaterfallBinding.inflate(LayoutInflater.from(context), parent, false)
    return ViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    if (position in 0 until itemCount) {
      holder.loadPic(waterfallPic[position]) {
        if (it) {
          changeLayoutManager(it)
        }
      }
    }
  }

  override fun getItemCount() = waterfallPic.size

  abstract fun changeLayoutManager(fillWidth: Boolean)
}