package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.markensic.core.framework.ui.dp
import org.markensic.learn.jetpack.R
import kotlin.math.max

class SquareImageView(
  context: Context,
  attrs: AttributeSet
) : AppCompatImageView(context, attrs) {

  val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = resources.getColor(R.color.purple_200)
    style = Paint.Style.STROKE
    strokeWidth = 2f.dp
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val maxSize = max(measuredHeight, measuredWidth)
    setMeasuredDimension(maxSize, maxSize)
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
  }
}
