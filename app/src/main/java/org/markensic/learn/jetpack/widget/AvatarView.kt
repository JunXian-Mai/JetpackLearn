package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.compressInWidth
import com.markensic.core.framework.ui.dp
import org.markensic.learn.jetpack.R

class AvatarView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet,
  defStyleAttr: Int = 0,
  defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

  val modeIn = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
  val modeOver = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)

  val padding = 5.dp
  val originBitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar)
  val paint = Paint(Paint.ANTI_ALIAS_FLAG)

  lateinit var srcBitmap: Bitmap
  lateinit var dstBitmap: Bitmap
  lateinit var borderBitmap: Bitmap
  lateinit var rectF: RectF

  lateinit var srcBitmap2: Bitmap

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
    srcBitmap = originBitmap.compressInWidth((width - padding).toInt())
    dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    borderBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    srcBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(dstBitmap)
    paint.color = Color.BLACK
    paint.style = Paint.Style.FILL
    canvas.drawOval(rectF, paint)

    canvas.setBitmap(borderBitmap)
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = padding.toFloat()
    val borderRectF =
      RectF(padding / 2f, padding / 2f, width - (padding / 2f), height - (padding / 2f))
    canvas.drawOval(borderRectF, paint)

    canvas.setBitmap(srcBitmap2)
    paint.color = Color.GREEN
    canvas.drawBitmap(srcBitmap, Rect(0, 0, width, height), rectF, paint)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
  }

  override fun onDraw(canvas: Canvas) {
    var count = canvas.saveLayer(rectF, paint)
    canvas.drawBitmap(dstBitmap, 0f, 0f, paint)
    paint.xfermode = modeIn
    canvas.drawBitmap(srcBitmap, 0f, 0f, paint)
    paint.xfermode = modeOver
    canvas.drawBitmap(borderBitmap, 0f, 0f, paint)
    paint.xfermode = null

//    paint.color = Color.BLACK
//    paint.style = Paint.Style.FILL
//    canvas.drawRect(rectF, paint)
//    paint.xfermode = modeIn
//    canvas.drawBitmap(srcBitmap2, 0f, 0f, paint)

    canvas.restoreToCount(count)
  }
}
