package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import org.markensic.baselibrary.api.utils.DisPlayUtils
import org.markensic.baselibrary.global.extensions.compress
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.learn.jetpack.R

class CameraView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet,
  defStyleAttr: Int = 0,
  defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

  val avatarWidth = 200.dp
  val padding = 50.dp
  val avatar = BitmapFactory.decodeResource(resources, R.drawable.avatar).compress(avatarWidth.toInt())
  lateinit var location: RectF
  val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  val pathHead = Path()
  val rotatedeg = 30f
  val camera = Camera().apply {
    rotateX(50f)
    setLocation(0f, 0f, -3.8f * DisPlayUtils.density)
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    location = RectF(0f, 0f, width.toFloat(), height.toFloat())
    pathHead.apply {
      lineTo(padding, padding)
      lineTo(padding + avatarWidth, padding)
      lineTo(padding + avatarWidth, padding + avatarWidth / 2f)
      lineTo(padding, avatarWidth + padding)
      lineTo(padding, padding)
    }
  }

  override fun onDraw(canvas: Canvas) {
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = 2.dp
    paint.color = Color.CYAN
    canvas.drawRect(location, paint)

    var offset = padding + avatarWidth / 2f
//    canvas.save()
//    canvas.translate(offset, offset)
////    canvas.clipPath(pathHead)
//    canvas.clipRect(-avatarWidth / 2f, -avatarWidth / 2f, avatarWidth / 2f, 0f)
//    canvas.translate(-offset, -offset)
//    canvas.drawBitmap(avatar, padding, padding, paint)
//    canvas.restore()
//
//    canvas.save()
//    canvas.translate(offset, offset)
//    camera.applyToCanvas(canvas)
//    canvas.clipRect(-avatarWidth / 2f, 0f, avatarWidth / 2f, avatarWidth / 2f)
//    canvas.translate(-offset, -offset)
//    canvas.drawBitmap(avatar, padding, padding, paint)
//    canvas.restore()

//    canvas.drawLine(0f, height / 2f, width.toFloat(), height /2f, paint)


    offset = padding + avatarWidth / 2f
    canvas.save()
    canvas.translate(offset, offset)
    canvas.rotate(-rotatedeg)
    canvas.clipRect(-offset, -height / 2f, width - offset, 0f)
    canvas.rotate(rotatedeg)
    canvas.translate(-offset, -offset)
    canvas.drawBitmap(avatar, padding, padding, paint)
    canvas.restore()

    canvas.save()
    canvas.translate(offset, offset)
    canvas.rotate(-rotatedeg)
    camera.applyToCanvas(canvas)
    canvas.clipRect(-offset, 0f, width - offset, height / 2f)
    canvas.rotate(rotatedeg)
    canvas.translate(-offset, -offset)
    canvas.drawBitmap(avatar, padding, padding, paint)
    canvas.restore()
  }
}