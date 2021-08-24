package org.markensic.learn.jetpack.viewmodels.adapter

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.markensic.core.framework.ui.Display
import com.markensic.core.global.log.CoreLog
import org.markensic.learn.jetpack.databinding.ItemWaterfallBinding

val fillWidthRatio = Display.realHeight.toFloat() / Display.realWidth

class Pointer(var left: Int, var right: Int)

class WaterfallAdapter(val context: Context) : RecyclerView.Adapter<WaterfallAdapter.ViewHolder>() {

    private lateinit var binding: ItemWaterfallBinding
    val waterfallPic = mutableListOf<String>()

    class ViewHolder(val binding: ItemWaterfallBinding) : RecyclerView.ViewHolder(binding.root) {

        fun loadPic(url: String) {
            binding.iv.load(url) {
                target {
                    (it as BitmapDrawable).apply {
                        val ratio = bitmap.height.toFloat() / bitmap.width
                        val height = Display.realWidth / 2 * ratio.toInt()
                        binding.iv.resetSize(Display.realWidth / 2, height)

                        binding.iv.setImageDrawable(this)
                    }
                }
            }
        }

        fun View.resetSize(width: Int, height: Int) {
            this.layoutParams.width = width
            this.layoutParams.height = height
            this.requestLayout()
        }

        private fun ImageView.restore(
            drawable: Drawable?,
            width: Int = Display.realWidth,
            height: Int = Display.realHeight
        ) {
            this.layoutParams.width = width
            this.layoutParams.height = height
            this.setImageDrawable(drawable)
            this.requestLayout()
        }

        fun restore() {
            binding.iv.restore(null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemWaterfallBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position in 0 until itemCount) {
            holder.loadPic(waterfallPic[position])
        }
    }

    override fun getItemCount() = waterfallPic.size

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        CoreLog.d("onViewRecycled: $holder, adapterPosition: ${holder.adapterPosition}, layoutPosition: ${holder.layoutPosition}")
        holder.restore()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }
}