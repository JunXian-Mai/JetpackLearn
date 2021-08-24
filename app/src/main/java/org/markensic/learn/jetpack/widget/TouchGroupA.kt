package org.markensic.learn.jetpack.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class TouchGroupA(c: Context, attributeSet: AttributeSet) : LinearLayout(c, attributeSet) {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.onInterceptTouchEvent(ev)
        Log.e(this::class.simpleName, "onInterceptTouchEvent")
        return result
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val result = super.dispatchTouchEvent(ev)
        Log.e(this::class.simpleName, "dispatchTouchEvent")
        return result
    }
}