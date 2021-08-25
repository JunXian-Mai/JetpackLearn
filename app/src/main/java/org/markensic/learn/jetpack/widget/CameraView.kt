package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.markensic.core.framework.ui.Display
import com.markensic.core.framework.ui.compressInWidth
import com.markensic.core.framework.ui.dp
import org.markensic.learn.jetpack.R

class CameraView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet,
  defStyleAttr: Int = 0,
  defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

  val avatarWidth = 200f.dp
  val padding = 50.dp.toFloat()
  val avatar = BitmapFactory.decodeResource(resources, R.drawable.avatar)
    .compressInWidth(avatarWidth.toInt())
  lateinit var location: RectF
  val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  val pathHead = Path()

  val camera = Camera()

  var headFilpeDeg = 0f
    set(value) {
      field = value
      invalidate()
    }

  var rotatedeg = 30f
    set(value) {
      field = value
      invalidate()
    }

  var bottomFilpeDeg = 50f
    set(value) {
      field = value
      invalidate()
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
    paint.strokeWidth = 2f.dp
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
    canvas.withSave {
      canvas.translate(offset, offset)
      canvas.rotate(-rotatedeg)
      camera.apply {
        save()
        rotateX(headFilpeDeg)
        setLocation(0f, 0f, -3.8f * Display.realDensity)
        applyToCanvas(canvas)
        restore()
      }
      canvas.clipRect(-avatarWidth, -avatarWidth, avatarWidth, 0f)
      canvas.rotate(rotatedeg)
      canvas.translate(-offset, -offset)
      canvas.drawBitmap(avatar, padding, padding, paint)
    }

    canvas.withSave {
      canvas.translate(offset, offset)
      canvas.rotate(-rotatedeg)
      camera.apply {
        save()
        rotateX(bottomFilpeDeg)
        setLocation(0f, 0f, -3.8f * Display.realDensity)
        applyToCanvas(canvas)
        restore()
      }
      canvas.clipRect(-avatarWidth, 0f, avatarWidth, avatarWidth)
      canvas.rotate(rotatedeg)
      canvas.translate(-offset, -offset)
      canvas.drawBitmap(avatar, padding, padding, paint)
    }
  }
}
