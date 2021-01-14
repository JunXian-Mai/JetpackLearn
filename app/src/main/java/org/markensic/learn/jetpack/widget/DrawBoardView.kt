package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import androidx.core.util.forEach
import org.markensic.baselibrary.global.extensions.dp

class DrawBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {
  private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = 5.dp
    strokeCap = Paint.Cap.ROUND
    strokeJoin = Paint.Join.ROUND
  }

  private val paths = SparseArray<Path>()

  override fun onDraw(canvas: Canvas) {
    paths.forEach { _, path ->
      Log.d(":test", "draw $path")
      canvas.drawPath(path, paint)
    }
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    when (event.actionMasked) {
      MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
        val actionIndex = event.actionIndex
        val actionId = event.getPointerId(actionIndex)
        val p = Path()
        p.moveTo(event.getX(actionIndex), event.getY(actionIndex))
        paths.append(actionId, p)
        invalidate()
      }
      MotionEvent.ACTION_MOVE -> {
        paths.forEach { id, path ->
          val index = event.findPointerIndex(id)
          path.lineTo(event.getX(index), event.getY(index))
        }
        invalidate()
      }
      MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
        val actionIndex = event.actionIndex
        val actionId = event.getPointerId(actionIndex)
        paths[actionId].reset()
        paths.remove(actionId)
        invalidate()
      }
    }
    return true
  }
}