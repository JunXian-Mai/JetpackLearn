package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.cos
import com.markensic.core.framework.ui.dp
import com.markensic.core.framework.ui.sin

class DashboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    val padding = 5f.dp

    val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val missAngle = 120f
    val startAngle = 90f + missAngle - (missAngle / 2f)
    val sweepAngle = 360f - missAngle
    val scaleCount = 20
    val unitAngle = sweepAngle / scaleCount
    lateinit var arcRectF: RectF
    val dashPath = Path()
    lateinit var dashPathMeasure: PathMeasure

    val scaleWidth = 2f.dp
    val scalHeight = 8f.dp
    val scaleRectF = RectF(0f, 0f, scaleWidth, scalHeight)
    val scalePath = Path()
    val scalePaint: Paint

    var pointLength = 0f
    val pointPaint: Paint

    init {
        dashPaint.color = Color.BLACK
        dashPaint.style = Paint.Style.STROKE
        dashPaint.strokeWidth = 2f.dp

        scalePaint = Paint(dashPaint)
        scalePath.addRect(scaleRectF, Path.Direction.CW)

        pointPaint = Paint(dashPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        arcRectF = RectF(padding, padding, width.toFloat() - padding, height.toFloat() - padding)
        pointLength = (width - (padding * 2)) / 2f - 30.dp
        dashPath.addArc(arcRectF, startAngle, sweepAngle)
        dashPathMeasure = PathMeasure(dashPath, false)

        scalePaint.pathEffect = PathDashPathEffect(
            scalePath,
            (dashPathMeasure.length - scaleWidth) / scaleCount,
            0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(dashPath, dashPaint)
        canvas.drawPath(dashPath, scalePaint)
        canvas.drawLine(
            arcRectF.centerX(), arcRectF.centerY(),
            getPointEndX(5),
            getPointEndY(5),
            pointPaint
        )
    }

    private fun getPointEndX(scaleIndex: Int) =
        arcRectF.centerX() + pointLength.cos(scaleIndex * unitAngle + startAngle)

    private fun getPointEndY(scaleIndex: Int) =
        arcRectF.centerY() + pointLength.sin(scaleIndex * unitAngle + startAngle)
}