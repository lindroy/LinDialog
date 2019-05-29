package com.lindroid.iosdialog

import android.content.DialogInterface
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroid.iosdialog.base.BaseIAlertDialog
import com.lindroid.iosdialog.util.getPxSize
import com.lindroid.iosdialog.util.getResColor
import com.lindroid.iosdialog.util.getResString
import com.lindroid.iosdialog.util.getSpSize
import kotlinx.android.synthetic.main.dialog_alert_ios.*

/**
 * @author Lin
 * @date 2019/5/17
 * @function iOS风格的提示对话框
 * @Description
 */
class IAlertDialog : BaseIAlertDialog<IAlertDialog>() {

    private var dismissible = true

    private var posListener: ((DialogInterface) -> Unit)? = null

    private var negListener: ((DialogInterface) -> Unit)? = null

    private var posBtnConfig = IDialog.alertPosBtnConfigs.copy()

    private var negBtnConfig = IDialog.alertNegBtnConfigs.copy()

    private var buttonHeight = IDialog.alertBtnHeight

    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    override var customViewId = R.layout.dialog_alert_ios

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) = IAlertDialog().apply {
            this.fm = fm
        }
    }

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        super.onHandleView(contentView)
        setAnimStyle(R.style.IOSAlertDialogStyle)
        btnPos.apply {
            posBtnConfig.let {
                text = it.text
                setTextColor(it.textColor)
                textSize = it.textSize
            }
            setOnClickListener {
                posListener?.invoke(dialog)
                if (dismissible) {
                    dismiss()
                }
            }
        }
        btnNeg.apply {
            visibility = when (isShowNegButton) {
                true -> {
                    negBtnConfig.let {
                        text = it.text
                        setTextColor(it.textColor)
                        textSize = it.textSize
                    }
                    setOnClickListener {
                        negListener?.invoke(dialog)
                        if (dismissible) {
                            dismiss()
                        }
                    }
                    View.VISIBLE
                }
                false -> View.GONE
            }
        }
        llRoot.background = initBackgroundDrawable()
        if (buttonHeight > 0) {
            val params = llButton.layoutParams
            params.height = buttonHeight
            llButton.layoutParams = params
        }
        return false
    }

    /**
     * 设置“确认”按钮文字
     */
    fun setPosButtonText(text: String) = this.apply { posBtnConfig.text = text }

    /**
     * 设置“确认”按钮文字
     * @param stringId:文字资源Id
     * @see setPosButtonText
     */
    fun setPosButtonText(@StringRes stringId: Int) = this.apply { setPosButtonText(getResString(stringId)) }

    /**
     * 设置“确认”按钮颜色
     */
    fun setPosButtonTextColor(@ColorInt color: Int) = this.apply { posBtnConfig.textColor = color }

    /**
     * 设置“确认”按钮颜色
     * @param colorId:颜色资源Id
     */
    fun setPosButtonTextColorRes(@ColorRes colorId: Int) =
            this.apply { setPosButtonTextColor(getResColor(colorId)) }

    /**
     * 设置“确认”按钮文字大小
     */
    fun setPosButtonTextSize(textSize: Float) = this.apply { posBtnConfig.textSize = textSize }

    /**
     * 设置“确认”按钮文字大小
     * @param textSizeId:文字大小Id
     */
    fun setPosButtonTextSizeRes(@DimenRes textSizeId: Int) =
            this.apply { setPosButtonTextSize(getSpSize(textSizeId)) }

    /**
     * “确认”按钮点击事件
     */
    fun setPosClickListener(listener: (DialogInterface) -> Unit) = this.apply { posListener = listener }

    /**
     * 设置“确认”按钮信息和点击事件
     */
    @JvmOverloads
    fun setPosButton(
            text: String = posBtnConfig.text,
            @ColorInt textColor: Int = posBtnConfig.textColor,
            textSize: Float = posBtnConfig.textSize,
            listener: (DialogInterface) -> Unit
    ) = this.apply {
        posBtnConfig.also {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
        }
        posListener = listener
    }

    /**
     * 设置“取消”按钮文字
     */
    fun setNegButtonText(text: String) = this.apply { negBtnConfig.text = text }

    /**
     * 设置“取消”按钮文字
     * @param stringId:文字资源Id
     * @see setNegButtonText
     */
    fun setNegButtonText(@StringRes stringId: Int) = this.apply { setNegButtonText(getResString(stringId)) }

    /**
     * 设置“取消”按钮文字颜色
     */
    fun setNegButtonTextColor(@ColorInt color: Int) = this.apply { negBtnConfig.textColor = color }

    /**
     * 设置“取消”按钮文字颜色
     * @param colorId:颜色资源Id
     */
    fun setNegButtonTextColorRes(@ColorRes colorId: Int) =
            this.apply { setNegButtonTextColor(getResColor(colorId)) }

    /**
     * 设置“取消”按钮文字大小
     */
    fun setNegButtonTextSize(textSize: Float) = this.apply { negBtnConfig.textSize = textSize }

    /**
     * 设置“取消”按钮文字大小
     * @param textSizeId:文字大小Id
     */
    fun setNegButtonTextSizeRes(@DimenRes textSizeId: Int) =
            this.apply { setNegButtonTextSize(getSpSize(textSizeId)) }

    /**
     * 设置“取消”按钮点击事件
     */
    fun setNegClickListener(listener: (DialogInterface) -> Unit) = this.apply { negListener = listener }

    /**
     * 设置“取消”按钮信息和点击事件
     */
    fun setNegButton(
            text: String = negBtnConfig.text,
            @ColorInt textColor: Int = negBtnConfig.textColor,
            textSize: Float = negBtnConfig.textSize,
            listener: (DialogInterface) -> Unit
    ) = this.apply {
        negBtnConfig.also {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
        }
        negListener = listener
    }

    /**
     * 设置“确定”按钮和“取消”按钮的高度
     * @param height:按钮高度，单位为px
     */
    fun setButtonHeight(height: Int) = this.apply { buttonHeight = height }

    /**
     * 设置“确定”按钮和“取消”按钮的高度
     * @param resId:dimens资源Id
     * @see setButtonHeight
     */
    fun setButtonHeightRes(@DimenRes resId: Int) = this.apply { setButtonHeight(getPxSize(resId)) }

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