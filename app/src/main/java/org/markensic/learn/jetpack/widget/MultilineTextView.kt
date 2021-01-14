package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import org.markensic.baselibrary.global.extensions.compressInWidth
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.baselibrary.global.extensions.sp
import org.markensic.learn.jetpack.R

class MultilineTextView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet,
  defStyleAttr: Int = 0,
  defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

  val avatarWidth = 150.dp
  val avatarPaddingTop = 55.dp
  val avatar = BitmapFactory.decodeResource(resources, R.drawable.avatar).compressInWidth(avatarWidth.toInt())
  val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut vel enim libero. In eu posuere augue. Proin placerat tempor faucibus. Proin facilisis eget ipsum eget semper. Vestibulum ut urna diam. Nulla fermentum condimentum nunc, eget maximus tortor luctus non. Praesent ut erat turpis. Vivamus consectetur dolor sed pellentesque cursus. Pellentesque at enim lorem."

  val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
  val textSize = 20.sp
  val bounds = Rect()
  val measuredWidth = floatArrayOf(0f)

  override fun onDraw(canvas: Canvas) {
    paint.strokeWidth = 2.dp
    paint.color = Color.GRAY
    canvas.drawLine(0f, 0f, width.toFloat(), 0f, paint)
    canvas.drawBitmap(avatar, width - avatarWidth, avatarPaddingTop, paint)

    textPaint.textSize = textSize
    textPaint.textAlign = Paint.Align.LEFT
    textPaint.getTextBounds(text, 0, text.length, bounds)
    var start = 0
    var count: Int
    var textWidth: Float
    var offsetX = -bounds.left.toFloat()
    var offsetY = -bounds.top.toFloat()
    while (start < text.length) {
      textWidth = if (offsetY + bounds.bottom < avatarPaddingTop
        || offsetY + bounds.top > avatarPaddingTop + avatarWidth) {
        width.toFloat()
      } else {
        width - avatarWidth
      }
      count = textPaint.breakText(text, start, text.length, true, textWidth, measuredWidth)
      canvas.drawText(text, start, start + count, offsetX, offsetY, textPaint)
      start += count
      offsetY += textPaint.fontSpacing
    }
  }
}