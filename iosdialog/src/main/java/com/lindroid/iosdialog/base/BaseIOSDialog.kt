package com.lindroid.iosdialog.base

import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.view.View
import com.lindroid.iosdialog.IDialog
import com.lindroid.iosdialog.bean.TextConfigs
import com.lindroid.lindialog.LinDialog
import com.lindroid.lindialog.base.BaseDialog
import kotlinx.android.synthetic.main.layout_alert_dialog_title.*

/**
 * @author Lin
 * @date 2019/5/18
 * @function iOS风格对话框基类
 * @Description
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseIOSDialog<T : BaseDialog<T>> : BaseDialog<T>() {

    private var radius = IDialog.cornerRadius

    private var bgColor = IDialog.bgColor

    private var bgAlpha = IDialog.alpha

    protected var isShowNegButton = true

    protected lateinit var titleConfig: TextConfigs

    protected lateinit var msgConfig: TextConfigs

    override fun onHandleView(contentView: View): Boolean {
        setWidthScale(IDialog.alertWidthScale)
        tvTitle.apply {
            visibility = when (titleConfig.text.isNotEmpty()) {
                true -> {
                    text = titleConfig.text
                    setTextColor(titleConfig.textColor)
                    textSize = titleConfig.textSize
                    gravity = titleConfig.gravity
                    View.VISIBLE
                }
                false -> View.GONE

            }

        }
        tvMessage.apply {
            visibility = when (msgConfig.text.isNotEmpty()) {
                true -> {
                    text = msgConfig.text
                    setTextColor(msgConfig.textColor)
                    textSize = msgConfig.textSize
                    gravity = msgConfig.gravity
                    View.VISIBLE
                }
                false -> View.GONE
            }
        }
        //标题下方的分割线
        viewDivider.visibility = if (titleConfig.text.isEmpty() && msgConfig.text.isEmpty()) View.GONE else View.VISIBLE
        return false
    }

    protected fun initShapeDrawable(): ShapeDrawable {
        val roundRectShape =
                RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
        return with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
    }

    fun setBackgroundColor(@ColorInt color: Int) = this.apply { bgColor = color }
    //    @android.support.annotation.FloatRange(from = 0.0, to = 1.0)
    fun setAlpha(alpha: Float) = this.apply { bgAlpha = alpha }

    /**
     * 设置背景圆角矩形的圆角半径，单位为px
     */
    fun setCornerRadius(radius: Int) = this.apply { this.radius = radius.toFloat() } as T

    fun setTitle(title: String) = this.apply { titleConfig.text = title } as T

    fun setTitle(@StringRes stringId: Int) = this.apply { setTitle(LinDialog.context.getString(stringId)) } as T

    fun setTitleSize(titleSize: Float) = this.apply { titleConfig.textSize = titleSize } as T

    fun setTitleColor(@ColorInt color: Int) = this.apply { titleConfig.textColor = color } as T

    fun setTitleColorId(@ColorRes colorId: Int) = this.apply { setTitleColor(LinDialog.getResColor(colorId)) } as T

    fun setMessage(message: String) = this.apply { msgConfig.text = message } as T

    fun setMessage(@StringRes stringId: Int) = this.apply { setMessage(LinDialog.context.getString(stringId)) } as T

    fun setMessageSize(messageSize: Float) = this.apply { msgConfig.textSize = messageSize } as T

    fun setMessageColor(@ColorInt color: Int) = this.apply { msgConfig.textColor = color } as T

    fun setMessageColorId(@ColorRes colorId: Int) = this.apply { setMessageColor(LinDialog.getResColor(colorId)) } as T

    fun setShowNegButton(isShowNegButton: Boolean) = this.apply { this.isShowNegButton = isShowNegButton } as T

}