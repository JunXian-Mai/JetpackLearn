package org.markensic.learn.jetpack.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import java.lang.Math.abs

class SimplerViewPager(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
  private var downX = 0f
  private var downY = 0f
  private var downScroll = 0
  private var currentPage = 0
  private var scrolling = false
  private val viewConfiguration = ViewConfiguration.get(context)
  private var pagingSlop = viewConfiguration.scaledPagingTouchSlop
  private var overScroller = OverScroller(context)
  private var velocityTracker = VelocityTracker.obtain()
  private var minVelocity = viewConfiguration.scaledMinimumFlingVelocity
  private var maxVelocity = viewConfiguration.scaledMaximumFlingVelocity


  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    var childL = 0
    var childT = 0
    var childR = width
    var childB = height
    children.forEach {
      it.layout(childL, childT, childR, childB)
      childL += width
      childR += width
    }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    measureChildren(widthMeasureSpec, heightMeasureSpec)
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
  }

  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    var result = false
    when (ev.actionMasked) {
      MotionEvent.ACTION_DOWN -> {
        scrolling = false
        downX = ev.x
        downY = ev.y
        downScroll = scrollX
      }
      MotionEvent.ACTION_MOVE -> {
        if (!scrolling) {
          val dx = downX - ev.x
          if (abs(dx) > pagingSlop) {
            scrolling = true
            parent.requestDisallowInterceptTouchEvent(true)
            result = true
          }
        }
      }
    }
    return result
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    initVelocityTracker(event)
    when (event.actionMasked) {
      MotionEvent.ACTION_DOWN -> {
        downX = event.x
        downY = event.y
        downScroll = scrollX
      }
      MotionEvent.ACTION_MOVE -> {
        val dMaxX = width * (childCount - 1)
        val dx =
          (downX - event.x + downScroll).coerceAtLeast(0f).coerceAtMost(dMaxX.toFloat())
        scrollTo(dx.toInt(), 0)
      }
      MotionEvent.ACTION_UP -> {
        docking()
        val preDownScroll = downScroll
        downScroll = scrollX
        velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
        val velocityX = velocityTracker.xVelocity
        val isFling =
          (abs(velocityX) > minVelocity) && abs(downScroll - preDownScroll) > abs(width / 2)
        if (isFling) {
          Log.d(":test", "currentPage: $currentPage")
          val scrollPage = computeScrollPage(velocityX)
          Log.d(":test", "scrollPage: $scrollPage")
          val targetPage =
            (currentPage + scrollPage).coerceAtLeast(0).coerceAtMost(childCount - 1)
          Log.d(":test", "targetPage: $targetPage")
          val scrollDistance = targetPage * width - downScroll
          Log.d(":test", "scrollDistance: $scrollDistance")
          currentPage = targetPage
          overScroller.startScroll(scrollX, 0, scrollDistance, 0)
          postInvalidateOnAnimation()
        }
      }
    }
    return true
  }

  private fun initVelocityTracker(event: MotionEvent) {
    if (event.actionMasked == MotionEvent.ACTION_DOWN) {
      velocityTracker.clear()
    }
    velocityTracker.addMovement(event)
  }

  private fun computeScrollPage(velocityX: Float): Int {
    val dVelocityX = (maxVelocity - minVelocity) / childCount
    var scrollPages = (abs(velocityX) - minVelocity) / dVelocityX
    scrollPages = scrollPages.coerceAtLeast(1f)
    if (velocityX > 0) {
      scrollPages = -scrollPages
    }
    return scrollPages.toInt()
  }

  override fun computeScroll() {
    if (overScroller.computeScrollOffset()) {
      scrollTo(overScroller.currX, overScroller.currY)
      postInvalidateOnAnimation()
    }
  }

  private fun docking() {
    val onePageSlipX = scrollX % width
    if (onePageSlipX != 0) {
      postOnAnimation {
        if (onePageSlipX > width / 2) {
          scrollTo(scrollX + (width - onePageSlipX), 0)
        } else {
          scrollTo(scrollX - onePageSlipX, 0)
        }
      }
    }
  }
}
