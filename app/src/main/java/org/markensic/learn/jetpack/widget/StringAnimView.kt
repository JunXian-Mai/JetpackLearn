package org.markensic.learn.jetpack.widget

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.dp


val stringList = arrayListOf(
  "蔷薇科",
  "芸香科",
  "葫芦科",
  "芭蕉科",
  "鼠李科",
  "葡萄科",
  "茶藨子科",
  "杜鹃花科",
  "漆树科",
  "猕猴桃科",
  "凤梨科",
  "杨梅科",
  "柿科",
  "番木瓜科",
  "桑科",
  "仙人掌科",
  "无患子科",
  "木棉科",
  "酢浆草科",
  "千屈菜科",
  "棕榈科",
  "藤黄科",
  "桃金娘科"
)

class StringAnimView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet,
  defStyleAttr: Int = 0,
  defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {


  var text = stringList[0]
    set(value) {
      field = value
      invalidate()
    }

  val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = Color.CYAN
    style = Paint.Style.STROKE
    strokeWidth = 2f.dp
  }

  val metrics = Paint.FontMetrics()
  val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    textSize = 22f.dp
    textAlign = Paint.Align.CENTER
    color = Color.GREEN
    getFontMetrics(metrics)
  }

  lateinit var location: RectF

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val w = MeasureSpec.getSize(widthMeasureSpec)
    val h = MeasureSpec.getSize(heightMeasureSpec)
    location = RectF(0f, 0f, w.toFloat(), h.toFloat())
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onDraw(canvas: Canvas) {
    canvas.drawText(
      text,
      width / 2f,
      height / 2f - (metrics.descent + metrics.ascent) / 2f,
      textPaint
    )

    canvas.drawRect(location, paint)
  }

  fun refreshToString(endText: String) {
    val anim = ObjectAnimator.ofObject(this, "text", StringEvaluator(), endText)
    anim.duration = 4000
    anim.startDelay = 1000
    anim.start()
  }

  class StringEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String, endValue: String): String {
      val sI = stringList.indexOf(startValue)
      val eI = stringList.indexOf(endValue)
      return stringList[sI + ((eI - sI) * fraction).toInt()]
    }

  }
}
