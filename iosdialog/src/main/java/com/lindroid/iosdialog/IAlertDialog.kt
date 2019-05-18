package com.lindroid.iosdialog

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.FloatRange
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroid.iosdialog.util.dp2px
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

    private var titleSize = 18F

    private var titleColor = LinDialog.getResColor(R.color.lin_dialog_text_color_black)

    private var message: String = ""

    private var messageSize = 16F

    private var messageColor = LinDialog.getResColor(R.color.lin_dialog_text_color_black)

    private var radius = dp2px(10F)

    private var bgColor = Color.WHITE

    private var bgAlpha = 0.85F

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
        tvTitle.apply {
            text = title
            setTextColor(titleColor)
            textSize = titleSize
        }
        tvMessage.apply {
            text = message
            setTextColor(messageColor)
            textSize = messageSize
        }

        initBackground()

        return false
    }

    private fun initBackground() {
        val roundRectShape = RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
        llRoot.background = with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
    }

    fun setTitle(title: String) = this.apply { this.title = title }

    fun setTitleSize(titleSize: Float) = this.apply { this.titleSize = titleSize }

    fun setTitleColor(@ColorInt color: Int) = this.apply { titleColor = color }

    fun setTitleColorId(@ColorRes colorId: Int) = this.apply { setTitleColor(LinDialog.getResColor(colorId)) }

    fun setMessage(message: String) = this.apply { this.message = message }

    fun setMessageSize(messageSize: Float) = this.apply { this.messageSize = messageSize }

    fun setMessageColor(@ColorInt color: Int) = this.apply { messageColor = color }

    fun setMessageColorId(@ColorRes colorId: Int) = this.apply { setMessageColor(LinDialog.getResColor(colorId)) }

    fun setBackgroundColor(@ColorInt color: Int) = this.apply { bgColor = color }

    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) = this.apply { bgAlpha = alpha }

    /**
     * 设置背景圆角矩形的圆角半径，单位为px
     */
    fun setCornerRaius(radius: Int) = this.apply { this.radius = radius.toFloat() }


}