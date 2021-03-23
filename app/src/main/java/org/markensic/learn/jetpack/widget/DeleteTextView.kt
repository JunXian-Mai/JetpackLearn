package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.and
import org.markensic.baselibrary.global.extensions.compressInWidth
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.learn.jetpack.R

class DeleteTextView(
  context: Context,
  attrs: AttributeSet
) : AppCompatTextView(context, attrs) {

  var showDeleteImage = false
    set(value) {
      if (field != value) {
        field = value
        invalidate()
      }
    }

  private val cancelButtonSize = 20.dp
  private val originCancelBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_cancel)
  private val cancelBitmap: Bitmap = originCancelBitmap.compressInWidth(cancelButtonSize.toInt())
  private val cancelPaint = Paint(Paint.ANTI_ALIAS_FLAG)

  private var cancelListener: ((View, ViewGroup) -> Unit)? = null

  init {
    setPadding(
      paddingLeft,
      (paddingTop + cancelButtonSize / 4).toInt(),
      (paddingRight + cancelButtonSize / 4).toInt(),
      paddingBottom
    )
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    if (showDeleteImage) {
      canvas.drawBitmap(cancelBitmap, width - cancelButtonSize, 0f, cancelPaint)
    }
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    when (event.actionMasked) {
      MotionEvent.ACTION_DOWN -> {
        if (showDeleteImage) {
          val xCondition = width - cancelButtonSize <= event.x && event.x <= width
          val yCondition = 0 <= event.y && event.y <= cancelButtonSize
          if (xCondition && yCondition) {
            cancelListener?.invoke(this, parent as ViewGroup)
            return true
          }
        }
      }
    }
    return super.onTouchEvent(event)
  }

  fun setCancelListener(l: (v: View, parent: ViewGroup) -> Unit) {
    cancelListener = l
  }
}