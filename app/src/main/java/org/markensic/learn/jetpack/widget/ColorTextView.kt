package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.baselibrary.global.extensions.sp

class ColorTextView(context: Context, attrs: AttributeSet): AppCompatTextView(context, attrs) {
  val colors = intArrayOf(
    Color.CYAN,
    Color.BLUE,
    Color.DKGRAY,
    Color.GRAY,
    Color.LTGRAY,
    Color.MAGENTA,
    Color.GREEN,
    Color.RED,
    Color.YELLOW,
    Color.LTGRAY
  )

  val colorSeed: Int
    get() {
      return (0 until colors.size).random()
    }

  val randomTextSize: Int
    get() {
      return (12..40).random()
    }

  val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG)

  val padding = 10.dp.toInt()

  init {
    setPadding(
      padding, padding, padding, padding
    )
  }

  init {
    paint.textSize = randomTextSize.sp
    text = stringList.random()
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), 2.dp, 2.dp, bgPaint.let {
      it.color = colors[colorSeed]
      it
    })


    super.onDraw(canvas)
  }
}