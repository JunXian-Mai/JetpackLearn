package org.markensic.learn.jetpack.widget

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import org.markensic.baselibrary.global.extensions.dp
import org.markensic.learn.jetpack.R
import java.lang.StringBuilder

class DragListenerView(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
  private val paddingRight = 10.dp
  private lateinit var avatarView1: View
  private lateinit var avatarView2: View
  private lateinit var descParentView: View
  private lateinit var descView: TextView
  private val dragListener = DragListener()

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    children.forEach {
      measureChild(it, widthMeasureSpec, heightMeasureSpec)
    }
    setMeasuredDimension(measuredWidth, measuredHeight)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    var childViewL = 0
    var childViewR = 0
    children.forEach {
      when(it) {
        is ViewGroup -> {
          it.layout(0, measuredHeight - it.measuredHeight, it.measuredWidth, measuredHeight)
        }
        is View -> {
          childViewR = childViewL + it.measuredWidth
          it.layout(childViewL, 0, childViewR, it.measuredHeight)
          childViewL += it.measuredWidth + paddingRight.toInt()
          childViewR += it.measuredWidth
        }
      }
    }
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    children.forEach {
      when(it) {
        is ViewGroup -> {
          descParentView = it
//          descView = descParentView.findViewById(R.id.desc)
        }
        is View -> {
//          if (it.id == R.id.img_1) {
//            avatarView1 = it
//          } else if (it.id == R.id.img_2) {
//            avatarView2 = it
//          }
          it.setOnLongClickListener {
            val data = ClipData(
              ClipDescription("contentDescription", arrayOf("contentDescription")),
              ClipData.Item(it.contentDescription)
            )
            it.startDrag(data, DragShadowBuilder(it), it, 0)
          }
        }
      }
      it.setOnDragListener(dragListener)
    }
  }

  val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
    style = Paint.Style.STROKE
    strokeWidth = 2.dp
    color = Color.CYAN
  }
  override fun dispatchDraw(canvas: Canvas) {
    super.dispatchDraw(canvas)
    canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
  }

  inner class DragListener: OnDragListener {
    override fun onDrag(v: View, event: DragEvent): Boolean {
      when(event.action) {
        DragEvent.ACTION_DROP -> {
          Log.d(":test", "$v drop!")
          if (v == descParentView) {
            val data = event.clipData
            val currentText = descView.text
            val builder = StringBuilder(currentText)
            builder.append(data.getItemAt(0).text)
            descView.text = builder.toString()
          }
        }
      }
      return true
    }
  }
}