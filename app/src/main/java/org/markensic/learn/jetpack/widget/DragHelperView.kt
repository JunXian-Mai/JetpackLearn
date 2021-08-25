package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper
import com.markensic.core.framework.ui.dp

class DragHelperView(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
  private val maxChildCount = 5
  private var childWidth = 0
  private val helper = ViewDragHelper.create(this, DragHelperCallBack())

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    val limitWidth = MeasureSpec.getSize(widthMeasureSpec)
    val limitHeight = MeasureSpec.getSize(heightMeasureSpec)
    childWidth = limitWidth / maxChildCount
    measureChildren(childWidth, limitHeight)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    var childL = 0
    var childT = 0
    var childR = childWidth
    var childB = height
    children.forEach {
      it.layout(childL, childT, childR, childB)
      childL += childWidth
      childR += childWidth
    }
  }

  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    return helper.shouldInterceptTouchEvent(ev)
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    helper.processTouchEvent(event)
    return true
  }

  val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = 2f.dp
    color = Color.CYAN
  }

  override fun dispatchDraw(canvas: Canvas) {
    super.dispatchDraw(canvas)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
  }

  inner class DragHelperCallBack : ViewDragHelper.Callback() {
    override fun tryCaptureView(child: View, pointerId: Int): Boolean {
      return true
    }

    override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
      return left
    }

    override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
      if (xvel > 0) {
        helper.settleCapturedViewAt(width - releasedChild.width, 0)
      } else {
        helper.settleCapturedViewAt(0, 0)
      }
      postInvalidateOnAnimation()
    }
  }

  override fun computeScroll() {
    if (helper.continueSettling(true)) {
      postInvalidateOnAnimation()
    }
  }
}
