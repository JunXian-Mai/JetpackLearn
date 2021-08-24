package org.markensic.learn.jetpack.widget

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.dp

class SideCircleview @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    val padding = 10f.dp

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var pointF = PointF(padding, padding)
        set(value) {
            field = value
            invalidate()
        }


    override fun onDraw(canvas: Canvas) {
        paint.color = Color.CYAN
        paint.strokeWidth = 10f.dp
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawPoint(pointF.x, pointF.y, paint)
    }


    fun silde(endPoint: PointF) {
        val anim = ObjectAnimator.ofObject(this, "pointF", PointEvaluator(), endPoint)
        anim.duration = 1000
        anim.startDelay = 1000
        anim.start()
    }

    class PointEvaluator : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val currentX = startValue.x + (endValue.x - startValue.x) * fraction
            val currentY = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(currentX, currentY)
        }
    }
}
