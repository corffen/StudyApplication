package com.kara.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import androidx.annotation.ColorInt

public inline fun roundBackgroundSpan(
    crossinline block: RoundBackgroundSpan.Builder.() -> Unit
): RoundBackgroundSpan =
    RoundBackgroundSpan.Builder().apply(block).build()

class RoundBackgroundSpan(builder: Builder) : ReplacementSpan() {
    private val leftPadding = builder.leftPadding
    private val rightPadding = builder.rightPadding
    private val topPadding = builder.topPadding
    private val bottomPadding = builder.bottomPadding
    private var backgroundColor: Int = builder.backgroundColor
    private var textColor: Int = builder.textColor
    private var radius: Float = builder.radius
    private var rectF: RectF? = null
    private var size = 0
    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        size = (paint.measureText(text, start, end) + leftPadding + rightPadding).toInt()
        val metrics = paint.fontMetricsInt
        if (fm != null) {
            fm.top = metrics.top - topPadding.toInt()
            fm.ascent = metrics.ascent
            fm.descent = metrics.descent
            fm.bottom = metrics.bottom + bottomPadding.toInt()
        }
        return size
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val originColor = paint.color
        paint.color = backgroundColor
        if (rectF == null) {
            rectF = RectF(x, top.toFloat(), x + size, bottom.toFloat())
        }
        rectF?.let {
            canvas.drawRoundRect(it, radius, radius, paint)
        }
        paint.color = textColor
        text?.let { canvas.drawText(it, start, end, x + leftPadding, y.toFloat(), paint) }
        paint.color = originColor
    }

    public class Builder {
        public var leftPadding: Float = 4f
        public var rightPadding: Float = 4f
        public var topPadding: Float = 4f
        public var bottomPadding: Float = 4f
        public var radius: Float = 4f

        @ColorInt
        public var backgroundColor: Int = Color.GREEN

        @ColorInt
        public var textColor: Int = Color.WHITE

        public fun setTextColor(@ColorInt value: Int): Builder = apply { this.textColor = value }
        public fun setBackgroundColor(@ColorInt value: Int): Builder = apply { this.backgroundColor = value }
        public fun setLeftPadding(value: Float): Builder = apply { this.leftPadding = value }
        public fun setRightPadding(value: Float): Builder = apply { this.rightPadding = value }
        public fun setTopPadding(value: Float): Builder = apply { this.topPadding = value }
        public fun setBottomPadding(value: Float): Builder = apply { this.bottomPadding = value }
        public fun setRadius(value: Float): Builder = apply { this.radius = value }

        fun build(): RoundBackgroundSpan = RoundBackgroundSpan(builder = this@Builder)
    }
}