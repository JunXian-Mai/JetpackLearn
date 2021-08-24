package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.cos
import com.markensic.core.framework.ui.dp
import com.markensic.core.framework.ui.sin

class PieChart @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    val padding = 5f.dp

    val colors = arrayOf(
        Color.parseColor("#6A6AFF"),
        Color.parseColor("#00CACA"),
        Color.parseColor("#00DB00"),
        Color.parseColor("#73BF00"),
        Color.parseColor("#AE8F00"),
        Color.parseColor("#BB3D00")
    )

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val sweepAngle = 360f / colors.size
    lateinit var rectF: RectF

    val offset = 5f.dp

    init {
        paint.style = Paint.Style.FILL
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rectF = RectF(padding, padding, width - padding, height - padding)
    }

    override fun onDraw(canvas: Canvas) {

        for (color in colors) {
            paint.color = color
            val offsetX = offset.cos(colors.indexOf(color) * sweepAngle + sweepAngle / 2)
            val offsetY = offset.sin(colors.indexOf(color) * sweepAngle + sweepAngle / 2)
            if (colors.indexOf(color) == 2) {
                val offsetRectF = RectF(
                    padding + offsetX,
                    padding + offsetY,
                    width - padding + offsetX,
                    height - padding + offsetY
                )
                canvas.drawArc(
                    offsetRectF,
                    colors.indexOf(color) * sweepAngle,
                    sweepAngle,
                    true,
                    paint
                )
            } else {
                canvas.drawArc(rectF, colors.indexOf(color) * sweepAngle, sweepAngle, true, paint)
            }
        }
    }

}