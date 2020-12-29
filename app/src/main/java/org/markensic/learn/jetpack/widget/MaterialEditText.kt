package org.markensic.learn.jetpack.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.learn.jetpack.R

class MaterialEditText (
  context: Context,
  attrs: AttributeSet
) : AppCompatEditText(context, attrs) {

  var labelTextSize = 11.dp
  var labelTextpaddingBottom = 3.dp
  var toPaddingTop = -1f

  val labelText = "LabelText"
  val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = resources.getColor(R.color.purple_500)
  }
  var labelShowing = false

  var animFraction: Float = 0f
    set(value) {
      if (field != value) {
        field = value
        invalidate()
      }
    }

  val anim by lazy {
    ObjectAnimator.ofFloat(this, "animFraction", 1f)
  }

  init {
    hint = labelText
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    if (toPaddingTop == -1f) {
      toPaddingTop = paddingTop + labelTextSize + labelTextpaddingBottom
      setPadding(paddingLeft, toPaddingTop.toInt(), paddingRight, paddingBottom)
    }
  }

  override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
    super.onTextChanged(text, start, lengthBefore, lengthAfter)
    if (!labelShowing && text?.isNotBlank() == true) {
      labelShowing = true
      anim.start()
    } else if (labelShowing && text?.isNotBlank() == false) {
      labelShowing = false
      anim.reverse()
    }
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    labelPaint.alpha = (animFraction * 0xFF).toInt()
    val drawLabelY = toPaddingTop - labelTextpaddingBottom + (1f - animFraction) * paint.textSize
    labelPaint.textSize = labelTextSize + (paint.textSize - labelTextSize) * (1f - animFraction)
    canvas.drawText(labelText, paddingLeft.toFloat(), drawLabelY, labelPaint)
  }

}