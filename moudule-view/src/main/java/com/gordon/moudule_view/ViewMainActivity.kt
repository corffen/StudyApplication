package com.gordon.moudule_view

import android.graphics.Color
import android.os.Bundle
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import com.gordon.moudule_view.databinding.ActivityViewMainBinding
import com.kara.widget.roundBackgroundSpan

class ViewMainActivity : AppCompatActivity() {
    private var _binding: ActivityViewMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityViewMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SpanUtils.with(binding.tvOther)
            .append("hello")
            .setSpans(roundBackgroundSpan {
                setTextColor(Color.WHITE)
                setLeftPadding(ConvertUtils.dp2px(4f).toFloat())
                setRightPadding(ConvertUtils.dp2px(4f).toFloat())

                setTopPadding(ConvertUtils.dp2px(2f).toFloat())
                setBottomPadding(ConvertUtils.dp2px(2f).toFloat())
                setRadius(ConvertUtils.dp2px(4f).toFloat())
            })
            .append("world")
            .setClickSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    ToastUtils.showShort("world")
                }
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = Color.BLUE
                    ds.isUnderlineText = false
                }
            })
            .create()
    }
}