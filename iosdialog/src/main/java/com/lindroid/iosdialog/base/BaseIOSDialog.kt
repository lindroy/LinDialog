package com.lindroid.iosdialog.base

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.FloatRange
import android.support.annotation.StringRes
import android.view.View
import com.lindroid.iosdialog.IDialog
import com.lindroid.iosdialog.bean.TextConfigs
import com.lindroid.iosdialog.util.getResColor
import com.lindroid.iosdialog.util.getResString
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

    protected var paddingTitleMsg = 0

    protected var paddingSides = 0

    protected var paddingTop = 0

    protected var paddingBottom = 0

    override fun onHandleView(contentView: View): Boolean {
        setWidthScale(IDialog.alertWidthScale)
        tvTitle.apply {
            visibility = when (titleConfig.text.isNotEmpty()) {
                true -> {
                    text = titleConfig.text
                    setTextColor(titleConfig.textColor)
                    textSize = titleConfig.textSize
                    gravity = titleConfig.gravity
                    setPadding(titleConfig.paddingLeft, titleConfig.paddingTop, titleConfig.paddingRight, titleConfig.paddingBottom)
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

        llTitleMsg.visibility = when (tvTitle.visibility == View.GONE && tvMessage.visibility == View.GONE) {
            true -> View.GONE
            false -> {
                llTitleMsg.setPadding(paddingSides, this@BaseIOSDialog.paddingTop, paddingSides, this@BaseIOSDialog.paddingBottom)
                //透明分割线，用于设置标题与信息文字的间距
                llTitleMsg.dividerDrawable = with(ShapeDrawable(RectShape())) {
                    intrinsicHeight = paddingTitleMsg
                    paint.color = Color.TRANSPARENT
                    this
                }
                View.VISIBLE
            }
        }
        return false
    }

    /**
     * 设置对话框背景
     */
    protected fun initBackgroundDrawable(): ShapeDrawable {
        val roundRectShape =
                RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
        return with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundColor(@ColorInt color: Int) = this.apply { bgColor = color } as T

    /**
     * 设置背景颜色
     * @param colorId:颜色资源Id
     */
    fun setBackgroundColorRes(@ColorRes colorId: Int) = this.apply { setBackgroundColor(getResColor(colorId)) } as T

    /**
     * 设置透明度
     */
    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) = this.apply { bgAlpha = alpha } as T

    /**
     * 设置背景圆角矩形的圆角半径，单位为px
     */
    fun setCornerRadius(radius: Int) = this.apply { this.radius = radius.toFloat() } as T

    /**
     * 设置标题文字
     */
    fun setTitle(title: String) = this.apply { titleConfig.text = title } as T

    /**
     * @see setTitle
     */
    fun setTitle(@StringRes stringId: Int) = this.apply { setTitle(getResString(stringId)) } as T

    /**
     * 设置标题文字大小
     */
    fun setTitleSize(titleSize: Float) = this.apply { titleConfig.textSize = titleSize } as T

    /**
     * 设置标题文字颜色
     */
    fun setTitleColor(@ColorInt color: Int) = this.apply { titleConfig.textColor = color } as T

    /**
     * 设置标题文字颜色
     * @param colorId：颜色资源Id
     */
    fun setTitleColorRes(@ColorRes colorId: Int) = this.apply { setTitleColor(getResColor(colorId)) } as T

    /**
     * 设置消息文字
     */
    fun setMessage(message: String) = this.apply { msgConfig.text = message } as T

    /**
     * @see setMessage
     */
    fun setMessage(@StringRes stringId: Int) = this.apply { setMessage(getResString(stringId)) } as T

    /**
     * 设置消息文字大小
     */
    fun setMessageSize(messageSize: Float) = this.apply { msgConfig.textSize = messageSize } as T

    /**
     * 设置消息文字颜色
     */
    fun setMessageColor(@ColorInt color: Int) = this.apply { msgConfig.textColor = color } as T

    /**
     * 设置消息文字颜色
     * @param colorId:颜色资源Id
     */
    fun setMessageColorRes(@ColorRes colorId: Int) = this.apply { setMessageColor(getResColor(colorId)) } as T

    /**
     * 是否显示取消按钮
     */
    fun setShowNegButton(isShowNegButton: Boolean) = this.apply { this.isShowNegButton = isShowNegButton } as T

}