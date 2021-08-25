package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.markensic.core.framework.ui.compressInWidth
import com.markensic.core.framework.ui.dp
import org.markensic.learn.jetpack.R

class MultiTouchView(context: Context, attrs: AttributeSet) : View(context, attrs) {

  private val targetWidth = 200f.dp
  private val avatar = BitmapFactory.decodeResource(resources, R.drawable.avatar)
    .compressInWidth(targetWidth.toInt())
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

  private var translateX = 0f
  private var translateY = 0f
  private var downX = 0f
  private var downY = 0f
  private var preTranslateX = 0f
  private var preTranslateY = 0f
  private var trackPointId = 0

  private var focusX = 0f
  private var focusY = 0f


  override fun onDraw(canvas: Canvas) {
    canvas.drawBitmap(avatar, translateX, translateY, paint)
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    //region 接力型
    val pointerCount = event.pointerCount
    when (event.actionMasked) {
      MotionEvent.ACTION_DOWN -> {
        trackPointId = event.getPointerId(0)
        downX = event.x
        downY = event.y
        preTranslateX = translateX
        preTranslateY = translateY
      }
      MotionEvent.ACTION_POINTER_DOWN -> {
        trackPointId = event.getPointerId(event.actionIndex)
        downX = event.getX(event.findPointerIndex(trackPointId))
        downY = event.getY(event.findPointerIndex(trackPointId))
        preTranslateX = translateX
        preTranslateY = translateY
      }
      MotionEvent.ACTION_MOVE -> {
        translateX =
          event.getX(event.findPointerIndex(trackPointId)) - downX + preTranslateX
        translateY =
          event.getY(event.findPointerIndex(trackPointId)) - downY + preTranslateY
        invalidate()
      }
      MotionEvent.ACTION_POINTER_UP -> {
        val newIndex = if (pointerCount - 1 == event.actionIndex) {
          pointerCount - 2
        } else {
          pointerCount - 1
        }
        trackPointId = event.getPointerId(newIndex)
        downX = event.getX(event.findPointerIndex(trackPointId))
        downY = event.getY(event.findPointerIndex(trackPointId))
        preTranslateX = translateX
        preTranslateY = translateY
      }
    }
    //endregion

    //region 协作型
    /*
    val pointerCount = event.pointerCount
    var sumX = 0f
    var sumY = 0f
    val isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
    if (isPointerUp) {
      val actionIndex = event.actionIndex
      for (index in 0 until pointerCount) {
        if (index != actionIndex) {
          sumX += event.getX(index)
          sumY += event.getY(index)
        }
      }
      focusX = sumX / (pointerCount - 1)
      focusY = sumY / (pointerCount - 1)
    } else {
      for (index in 0 until pointerCount) {
        sumX += event.getX(index)
        sumY += event.getY(index)
      }
      focusX = sumX / pointerCount
      focusY = sumY / pointerCount
    }
    when(event.actionMasked) {
      MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
        downX = focusX
        downY = focusY
        preTranslateX = translateX
        preTranslateY = translateY
      }
      MotionEvent.ACTION_MOVE -> {
        translateX = focusX - downX + preTranslateX
        translateY = focusY - downY + preTranslateY
        invalidate()
      }
    }
    */
    //endregion
    return true
  }
}
