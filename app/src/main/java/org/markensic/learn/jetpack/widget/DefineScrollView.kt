package org.markensic.learn.jetpack.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ScrollView

class DefineScrollView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {

    private var downX = 0f
    private var downY = 0f
    private var dx = 0f
    private var dy = 0f
    private var interceptTouchEvent = false
    private var touchEvent = false

    private var thisReadySlide = false
    private var scrolling = false

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(":test", "onInterceptTouchEvent: ACTION_DOWN")
                downX = ev.x
                downY = ev.y
                interceptTouchEvent = true
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(":test", "onInterceptTouchEvent: ACTION_DOWN")
                dx = ev.x - downX
                dy = ev.y - downY
                thisReadySlide = canScrollVertically(dy.toInt())
                if (!scrolling) {
                    Log.d(":test", "thisReadySlide: $thisReadySlide")
                    if (thisReadySlide) {
                        scrolling = true
                        interceptTouchEvent = true
                        parent.requestDisallowInterceptTouchEvent(true)
                        touchEvent = true
                    } else {
                        interceptTouchEvent = false
                        parent.requestDisallowInterceptTouchEvent(false)
                        touchEvent = false
                    }
                }
            }
        }
        return interceptTouchEvent
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(":test", "onTouchEvent: ACTION_DOWN")
                downX = event.x
                downY = event.y
                touchEvent = true
                super.onTouchEvent(event)
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(":test", "onTouchEvent: ACTION_MOVE")
                dx = event.x - downX
                dy = event.y - downY
                Log.d(":test", "dy: $dy")
                thisReadySlide = canScrollVertically((-dy).toInt())
                Log.d(":test", "thisReadySlide: $thisReadySlide")
                if (thisReadySlide) {
                    interceptTouchEvent = true
                    parent.requestDisallowInterceptTouchEvent(true)
                    touchEvent = true
                } else {
                    interceptTouchEvent = false
                    parent.requestDisallowInterceptTouchEvent(false)
                    touchEvent = false
                }
                super.onTouchEvent(event)
            }
        }

        return touchEvent
    }
}