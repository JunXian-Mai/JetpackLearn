package org.markensic.learn.jetpack.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.markensic.core.framework.ui.dp

class RingTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    val mode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
    val padding = 5f.dp

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var text = "abab"
    val textSize = 80f.dp
    lateinit var layerRectF: RectF
    lateinit var rectF: RectF
    val bounds: Rect = Rect()
    lateinit var srcBitmap: Bitmap
    lateinit var dstBitmap: Bitmap

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        layerRectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
        rectF = RectF(padding, padding, width - padding, height - padding)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f.dp

        srcBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        dstBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(dstBitmap)
        paint.color = Color.GRAY
        canvas.drawOval(rectF, paint)

        canvas.setBitmap(srcBitmap)
        paint.color = Color.CYAN
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(rectF, -90f, 225f, false, paint)
    }

    override fun onDraw(canvas: Canvas) {
        val count = canvas.saveLayer(layerRectF, paint)
        canvas.drawBitmap(dstBitmap, 0f, 0f, paint)
        paint.xfermode = mode
        canvas.drawBitmap(srcBitmap, 0f, 0f, paint)
        paint.xfermode = null
        canvas.restoreToCount(count)

        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = textSize
        textPaint.color = Color.BLUE
        val metrics = textPaint.fontMetrics
        textPaint.getFontMetrics(metrics)

//    paint.style = Paint.Style.FILL
//    paint.strokeWidth = 2.dp
//    canvas.drawLine(0f, height / 2f, width.toFloat(), height / 2f, paint)

        var centerY = (metrics.ascent + metrics.descent) / 2f
        textPaint.color = Color.BLUE
        canvas.drawText(text, width / 2f, height / 2f - centerY, textPaint)

//    textPaint.getTextBounds(text, 0, text.length, bounds)
//    centerY = (bounds.bottom + bounds.top) / 2f
//    textPaint.color = Color.RED
//    canvas.drawText(text, width / 2f, height / 2f - centerY, textPaint)
    }
}