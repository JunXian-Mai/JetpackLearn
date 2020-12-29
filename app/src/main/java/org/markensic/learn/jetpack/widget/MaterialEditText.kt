package org.markensic.learn.jetpack.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.learn.jetpack.R

class MaterialEditText (
  context: Context,
  attrs: AttributeSet
) : AppCompatEditText(context, attrs) {

  var defaultPaddingTop = -1

  var toPaddingTop = -1f

  var labelTextpaddingBottom = 3.dp

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

  var labelShowing = false

  var enableFloatLabel = false
    set(value) {
      if (value != field) {
        field = value
        if (field) {
          if (defaultPaddingTop == -1) defaultPaddingTop = paddingTop
          toPaddingTop = defaultPaddingTop + labelTextSize + labelTextpaddingBottom
          setPadding(paddingLeft, toPaddingTop.toInt(), paddingRight, paddingBottom)
        } else {
          setPadding(paddingLeft, defaultPaddingTop, paddingRight, paddingBottom)
        }
        invalidate()
      }
    }

  val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = resources.getColor(R.color.purple_500)
  }

  var labelText: String = ""
    set(value) {
      if (value != field) {
        field = value
        invalidate()
      }
    }

  var labelTextSize = 11.dp
    set(value) {
      if (value != field) {
        field = value
        labelPaint.textSize = field
        invalidate()
      }
    }

  var labelTextColor: Int = 0
    set(value) {
      if (value != field) {
        field = value
        labelPaint.color = field
        invalidate()
      }
    }

  init {
    val defaultLabelText = hint.toString()
    val defaultLabelSize = if(paint.textSize - 7.dp > 0) paint.textSize - 7.dp else 11.dp
    val defaultLabelColor = hintTextColors.getColorForState(intArrayOf(android.R.attr.hint), hintTextColors.defaultColor)

    val arrayAttrs = intArrayOf(R.attr.enableFloatLabel, R.attr.floatLabelText, R.attr.floatLabelSize, R.attr.floatLabelColor)
    val ty = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
    enableFloatLabel = ty.getBoolean(R.styleable.MaterialEditText_enableFloatLabel, true)
    labelText = ty.getString(R.styleable.MaterialEditText_floatLabelText) ?: defaultLabelText
    labelTextSize = ty.getDimension(R.styleable.MaterialEditText_floatLabelSize, defaultLabelSize)
    labelTextColor = ty.getColor(R.styleable.MaterialEditText_floatLabelColor, defaultLabelColor)
    ty.recycle()
  }

  override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
    super.onTextChanged(text, start, lengthBefore, lengthAfter)
    if (enableFloatLabel) {
      if (!labelShowing && text?.isNotBlank() == true) {
        labelShowing = true
        anim.start()
      } else if (labelShowing && text?.isNotBlank() == false) {
        labelShowing = false
        anim.reverse()
      }
    }
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    if (enableFloatLabel) {
      labelPaint.alpha = (animFraction * 0xFF).toInt()
      val drawLabelY = toPaddingTop - labelTextpaddingBottom + (1f - animFraction) * paint.textSize
      labelPaint.textSize = labelTextSize + (paint.textSize - labelTextSize) * (1f - animFraction)
      canvas.drawText(labelText, paddingLeft.toFloat(), drawLabelY, labelPaint)
    }
  }
}