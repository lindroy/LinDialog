package com.lindroid.iosdialog

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroid.lindialog.LinDialog
import com.lindroid.lindialog.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_alert_ios.*

/**
 * @author Lin
 * @date 2019/5/17
 * @function iOS风格的提示对话框
 * @Description
 */
class IAlertDialog : BaseDialog<IAlertDialog>() {

    private var title: String = ""

    private var message: String = ""

    private var radius = 50F

    private var bgColor = Color.WHITE

    private var bgAlpha = 0.8F

    private var posText = LinDialog.context.getText(R.string.ios_dialog_positive_text)

    private var negText = LinDialog.context.getText(R.string.ios_dialog_negative_text)

    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    override var customViewId = R.layout.dialog_alert_ios

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                IAlertDialog().apply {
                    this.fm = fm
                }
    }

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        setWidthScale(0.7F)
        setAnimStyle(R.style.ScaleDialogStyle)
        tvTitle.text = title
        tvMessage.text = message
        val roundRectShape = RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
        val shapeDrawable = with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
        llRoot.background = shapeDrawable
        return false
    }

    fun setTitle(title: String) = this.apply { this.title = title }

    fun setMessage(message: String) = this.apply { this.message = message }

    fun setBackgroundColor(@ColorInt color:Int) = this.apply { bgColor = color }

    fun setAlpha(@FloatRange(from = 0.0,to = 1.0) alpha:Float) = this.apply { bgAlpha = alpha }

    fun setCornerRaius(radius:Int) = this.apply { this.radius = radius.toFloat() }

}