package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import org.markensic.baselibrary.global.extensions.dp
import kotlin.math.max

class TabLayout(context: Context, attrs: AttributeSet): ViewGroup(context, attrs) {
  val childrenBounds = mutableListOf<Rect>()

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
    val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)

    var widthUsed = 0
    var heightUsed = 0
    var lineWidth = 0
    var lineHeight = 0

    for ((index, child) in children.withIndex()) {
      measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
      if ((lineWidth + child.measuredWidth) >= specWidthSize
        && specWidthMode != MeasureSpec.UNSPECIFIED) {
        heightUsed += lineHeight
        lineWidth = 0
        lineHeight = 0
        measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
      }

      if (childrenBounds.size < index + 1) {
        childrenBounds.add(
          Rect(
            lineWidth,
            heightUsed,
            lineWidth + child.measuredWidth,
            heightUsed + child.measuredHeight
          )
        )
      } else {
        childrenBounds[index]
          .set(lineWidth, heightUsed, lineWidth + child.measuredWidth, heightUsed + child.measuredHeight)
      }

      lineWidth += child.measuredWidth
      widthUsed = max(widthUsed, lineWidth)
      lineHeight = max(lineHeight, child.measuredHeight)
    }

    setMeasuredDimension(
      widthUsed,
      heightUsed + lineHeight
    )
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    for ((index, child) in children.withIndex()) {
      val r = childrenBounds[index]
      child.layout(r.left, r.top, r.right, r.bottom)
    }
  }

  override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
    return MarginLayoutParams(context, attrs)
  }
}