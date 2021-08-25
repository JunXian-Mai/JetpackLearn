package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.dp
import org.markensic.learn.jetpack.R

class CircleView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet,
  defStyleAttr: Int = 0,
  defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

  val padding = 30f.dp
  var radius = 100f.dp

  val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.FILL
    strokeWidth = 2f.dp
    color = resources.getColor(R.color.teal_200)
  }

  val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.FILL
    color = resources.getColor(R.color.purple_200)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val targetWidth = (radius + padding) * 2
    val finalWidth = resolveSize(targetWidth.toInt(), widthMeasureSpec)
    val finalHeight = resolveSize(targetWidth.toInt(), heightMeasureSpec)
    setMeasuredDimension(finalWidth, finalHeight)
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

    val centerX = width / 2f
    val centerY = padding + radius
    canvas.drawCircle(centerX, centerY, radius, circlePaint)

    paint.style = Paint.Style.STROKE
    paint.color = resources.getColor(R.color.purple_200)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
  }
}
