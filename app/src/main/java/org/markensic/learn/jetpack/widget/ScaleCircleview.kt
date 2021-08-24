package org.markensic.learn.jetpack.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.dp

class ScaleCircleview @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var radius = 0f.dp
        set(value) {
            field = value
            invalidate()
        }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        radius = w / 2f - 5.dp
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        paint.color = Color.CYAN
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }


    fun scaleCircleWithScale(radius: Float) {
        val scaleRadius = radius / this.radius
        this.animate().scaleX(scaleRadius).scaleY(scaleRadius).setStartDelay(1000).setDuration(1000)
            .withLayer()
    }

    fun scaleCircleWithAnime(radius: Float) {
        val anim = ObjectAnimator.ofFloat(this, "radius", radius)
        anim.duration = 1000
        anim.startDelay = 1000
        anim.start()
    }
}
