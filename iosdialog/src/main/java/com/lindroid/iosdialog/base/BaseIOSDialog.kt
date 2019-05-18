package com.lindroid.iosdialog.base

import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.FloatRange
import android.view.View
import com.lindroid.iosdialog.IDialog
import com.lindroid.lindialog.LinDialog
import com.lindroid.lindialog.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_alert_ios.*

/**
 * @author Lin
 * @date 2019/5/18
 * @function iOS风格对话框基类
 * @Description
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseIOSDialog<T : BaseDialog<T>>: BaseDialog<T>() {

    protected var title: String = ""

    protected var titleSize = IDialog.titleConfigs.textSize

    protected var titleColor = IDialog.titleConfigs.textColor

    protected var message: String = ""

    protected var messageSize = IDialog.msgConfigs.textSize

    protected var messageColor = IDialog.msgConfigs.textColor

    protected var radius = IDialog.cornerRadius

    protected var bgColor = IDialog.bgColor

    protected var bgAlpha = IDialog.alpha

    override fun onHandleView(contentView: View): Boolean {
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
        return false
    }

    protected fun initShapeDrawable(): ShapeDrawable {
        val roundRectShape = RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
        return with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
    }

    fun setBackgroundColor(@ColorInt color: Int) = this.apply { bgColor = color }

    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) = this.apply { bgAlpha = alpha }

    /**
     * 设置背景圆角矩形的圆角半径，单位为px
     */
    fun setCornerRadius(radius: Int) = this.apply { this.radius = radius.toFloat() } as T

    fun setTitle(title: String) = this.apply { this.title = title } as T

    fun setTitleSize(titleSize: Float) = this.apply { this.titleSize = titleSize } as T

    fun setTitleColor(@ColorInt color: Int) = this.apply { titleColor = color } as T

    fun setTitleColorId(@ColorRes colorId: Int) = this.apply { setTitleColor(LinDialog.getResColor(colorId)) } as T

    fun setMessage(message: String) = this.apply { this.message = message } as T

    fun setMessageSize(messageSize: Float) = this.apply { this.messageSize = messageSize } as T

    fun setMessageColor(@ColorInt color: Int) = this.apply { messageColor = color } as T

    fun setMessageColorId(@ColorRes colorId: Int) = this.apply { setMessageColor(LinDialog.getResColor(colorId)) } as T

}