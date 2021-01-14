package org.markensic.learn.jetpack.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.OverScroller
import androidx.core.view.ViewCompat
import org.markensic.baselibrary.global.extensions.compress
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.learn.jetpack.R
import kotlin.math.max
import kotlin.math.min

class ScalableImageView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

  private val boardPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    color = Color.CYAN
    style = Paint.Style.STROKE
    strokeWidth = 2.dp
  }

  val gestureDetectorListener = GestureDetectorListener()
  val gestureDetector = GestureDetector(context, gestureDetectorListener)
  val paint = Paint(Paint.ANTI_ALIAS_FLAG)
  val imageWidth = 300.dp
  val originAvatar = BitmapFactory.decodeResource(resources, R.drawable.avatar)
  lateinit var avatar: Bitmap
  lateinit var originP: PointF
  var scale = 0f
  var scaleOffset = 0.3f
  var normal = true
  var translateX = 0f
  var translateY = 0f
  val overScroller = OverScroller(context)

  var currentScale = 1f
    set(value) {
      if (field != value) {
        field = value
        invalidate()
      }
    }

  val scaleAnimation = ObjectAnimator.ofFloat(this, "currentScale", 1f, scale).apply {
    interpolator = DecelerateInterpolator()
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    avatar = if (originAvatar.width >= originAvatar.height) {
      val compressH = width / (originAvatar.width / originAvatar.height)
      originP = PointF(0f, (height - compressH) / 2f)
      scale = height.toFloat() / compressH + scaleOffset
      originAvatar.compress(width)
    } else {
      val compressW = originAvatar.width / originAvatar.height * height
      originP = PointF((width - compressW) / 2f, 0f)
      scale = width.toFloat() / compressW + scaleOffset
      originAvatar.compress(compressW)
    }
    scaleAnimation.setFloatValues(1f, scale)
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), boardPaint)

    val scaleFraction = (currentScale - 1)/ (scale - 1)
    canvas.translate(translateX * scaleFraction, translateY * scaleFraction)
    canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
    canvas.drawBitmap(avatar, originP.x, originP.y, paint)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    return gestureDetector.onTouchEvent(event)
  }


  private fun fixOffsets() {
    translateX = translateX.coerceAtLeast(-(avatar.width * scale - width) / 2).coerceAtMost((avatar.width * scale - width) / 2)
    translateY = translateY.coerceAtLeast(-(avatar.height * scale - height) / 2).coerceAtMost((avatar.height * scale - height) / 2)
//    translateX = min(translateX, (avatar.width * scale - width) / 2)
//    translateX = max(translateX, -(avatar.width * scale - width) / 2)
//    translateY = min(translateY, (avatar.height * scale - height) / 2)
//    translateY = max(translateY, -(avatar.height * scale - height) / 2)
  }

  inner class GestureDetectorListener : GestureDetector.SimpleOnGestureListener(){

    private val runnable = TranslateRun()

    override fun onDown(e: MotionEvent?): Boolean = true

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
      if (!normal) {
        translateX -= distanceX
        translateY -= distanceY
        fixOffsets()
        invalidate()
      }
      return false
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
      if (!normal) {
        val dx = ((avatar.width * scale - width) / 2).toInt()
        val dy = ((avatar.height * scale - height) / 2).toInt()
        overScroller.fling(
          translateX.toInt(), translateY.toInt(),
          velocityX.toInt(), velocityY.toInt(),
          -dx, dx,
          -dy, dy,
          30.dp.toInt(), 30.dp.toInt()
        )
        ViewCompat.postOnAnimation(this@ScalableImageView, runnable)
      }
      return false
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
      normal = if (normal) {
        translateX = -(e.x - width / 2f) * (scale - 1)
        translateY = -(e.y - height / 2f) * (scale - 1)
        fixOffsets()
        scaleAnimation.start()
        false
      } else {
        scaleAnimation.reverse()
        true
      }
      return false
    }
  }

  inner class TranslateRun : Runnable {
    override fun run() {
      if (overScroller.computeScrollOffset()) {
        translateX = overScroller.currX.toFloat()
        translateY = overScroller.currY.toFloat()
        fixOffsets()
        invalidate()

        ViewCompat.postOnAnimation(this@ScalableImageView, this)
      }
    }

  }
}