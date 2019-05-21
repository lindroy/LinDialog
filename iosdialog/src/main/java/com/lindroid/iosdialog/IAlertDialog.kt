package com.lindroid.iosdialog

import android.content.DialogInterface
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroid.iosdialog.base.BaseIOSDialog
import kotlinx.android.synthetic.main.dialog_alert_ios.*

/**
 * @author Lin
 * @date 2019/5/17
 * @function iOS风格的提示对话框
 * @Description
 */
class IAlertDialog : BaseIOSDialog<IAlertDialog>() {

    private var dismissible = true

    private var posListener: ((DialogInterface) -> Unit)? = null

    private var negListener: ((DialogInterface) -> Unit)? = null

    private var posBtnConfig = IDialog.alertPosBtnConfigs.copy()

    private var negBtnConfig = IDialog.alertNegBtnConfigs.copy()

    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    override var customViewId = R.layout.dialog_alert_ios

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) = IAlertDialog().apply {
            this.fm = fm
            titleConfig = IDialog.alertTitleConfigs.copy()
            msgConfig = IDialog.alertMsgConfigs.copy()
            paddingTitleMsg = IDialog.alertPaddingTitleMsg
            paddingTop = IDialog.alertPaddingTop
            paddingSides = IDialog.alertPaddingSides
            paddingBottom = IDialog.alertPaddingBottom
        }
    }

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        super.onHandleView(contentView)
        setAnimStyle(R.style.AlertDialogStyle)
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
        llRoot.background = initShapeDrawable()

        return false
    }

    fun setPosButtonText(text: String) = this.apply { posBtnConfig.text = text }

    fun setPosButtonTextColor(@ColorInt color: Int) = this.apply { posBtnConfig.textColor = color }

    fun setPosButtonTextColorId(@ColorRes colorId: Int) =
            this.apply { setPosButtonTextColor(IDialog.getResColor(colorId)) }

    fun setPosButtonTextSize(textSize: Float) = this.apply { posBtnConfig.textSize = textSize }

    fun setPosButtonTextSizeId(@DimenRes textSizeId: Int) =
            this.apply { setPosButtonTextSize(IDialog.getSpSize(textSizeId)) }

    fun setPosClickListener(listener: (DialogInterface) -> Unit) = this.apply { posListener = listener }

    fun setNegButtonText(text: String) = this.apply { negBtnConfig.text = text }

    fun setNegButtonTextColor(@ColorInt color: Int) = this.apply { negBtnConfig.textColor = color }

    fun setNegButtonTextColorId(@ColorRes colorId: Int) =
            this.apply { setNegButtonTextColor(IDialog.getResColor(colorId)) }

    fun setNegButtonTextSize(textSize: Float) = this.apply { negBtnConfig.textSize = textSize }

    fun setNegButtonTextSizeId(@DimenRes textSizeId: Int) =
            this.apply { setNegButtonTextSize(IDialog.getSpSize(textSizeId)) }

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