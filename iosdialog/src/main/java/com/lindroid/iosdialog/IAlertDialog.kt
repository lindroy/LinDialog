package com.lindroid.iosdialog

import android.content.DialogInterface
import android.support.annotation.ColorInt
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
        fun build(fm: FragmentManager) = IAlertDialog().apply { this.fm = fm }
    }

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        super.onHandleView(contentView)

        setAnimStyle(R.style.ScaleDialogStyle)
        btnPos.apply {
            text = posText
            setTextColor(posTextColor)
            textSize = posTextSize
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
                    text = negText
                    setTextColor(negTextColor)
                    textSize = negTextSize
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