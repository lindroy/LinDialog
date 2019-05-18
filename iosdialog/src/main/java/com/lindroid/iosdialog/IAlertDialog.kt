package com.lindroid.iosdialog

import android.content.DialogInterface
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
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

    private var titleSize = IDialog.titleConfigs.textSize

    private var titleColor = IDialog.titleConfigs.textColor

    private var message: String = ""

    private var messageSize = IDialog.msgConfigs.textSize

    private var messageColor = IDialog.msgConfigs.textColor

    private var radius = IDialog.cornerRadius

    private var bgColor = IDialog.bgColor

    private var bgAlpha = IDialog.alpha

    private var dismissible = true

    private var posText = IDialog.posButtonConfigs.text

    private var posTextSize = IDialog.posButtonConfigs.textSize

    private var posTextColor = IDialog.posButtonConfigs.textColor

    private var posListener: ((DialogInterface) -> Unit)? = null

    private var negText = IDialog.negButtonConfigs.text

    private var negTextSize = IDialog.posButtonConfigs.textSize

    private var negTextColor = IDialog.negButtonConfigs.textColor

    private var negListener: ((DialogInterface) -> Unit)? = null

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
        btnPos.apply {
            text = posText
            setTextColor(posTextColor)
            textSize = posTextSize
            setOnClickListener {
                posListener?.invoke(dialog)
                if (dismissible){
                    dismiss()
                }
            }
        }
        btnNeg.apply {
            text = negText
            setTextColor(negTextColor)
            textSize = negTextSize
            setOnClickListener {
                negListener?.invoke(dialog)
                if (dismissible){
                    dismiss()
                }
            }
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

    fun setBackgroundColor(@ColorInt color: Int) = this.apply { bgColor = color }

    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) = this.apply { bgAlpha = alpha }

    /**
     * 设置背景圆角矩形的圆角半径，单位为px
     */
    fun setCornerRaius(radius: Int) = this.apply { this.radius = radius.toFloat() }

    fun setTitle(title: String) = this.apply { this.title = title }

    fun setTitleSize(titleSize: Float) = this.apply { this.titleSize = titleSize }

    fun setTitleColor(@ColorInt color: Int) = this.apply { titleColor = color }

    fun setTitleColorId(@ColorRes colorId: Int) = this.apply { setTitleColor(LinDialog.getResColor(colorId)) }

    fun setMessage(message: String) = this.apply { this.message = message }

    fun setMessageSize(messageSize: Float) = this.apply { this.messageSize = messageSize }

    fun setMessageColor(@ColorInt color: Int) = this.apply { messageColor = color }

    fun setMessageColorId(@ColorRes colorId: Int) = this.apply { setMessageColor(LinDialog.getResColor(colorId)) }

    fun setPosButtonText(text: String) = this.apply { posText = text }

    fun setPosButtonTextColor(@ColorInt color: Int) = this.apply { posTextColor = color }

    fun setPosButtonTextSize(textSize: Float) = this.apply { posTextSize = textSize }

    fun setPosClickListener(listener: (DialogInterface) -> Unit) = this.apply { posListener = listener }

    fun setNegButtonText(text: String) = this.apply { negText = text }

    fun setNegButtonTextColor(@ColorInt color: Int) = this.apply { negTextColor = color }

    fun setNegButtonTextSize(textSize: Float) = this.apply { negTextSize = textSize }

    fun setNegClickListener(listener: (DialogInterface) -> Unit) = this.apply { negListener = listener }

    /**
     * 点击对话框上的按钮是否可以关闭对话框，默认为true
     */
    fun setCanDismiss(dismissible: Boolean) = this.apply { this.dismissible = dismissible }

    override fun onDestroy() {
        super.onDestroy()
        negListener = null
        posListener = null
    }
}