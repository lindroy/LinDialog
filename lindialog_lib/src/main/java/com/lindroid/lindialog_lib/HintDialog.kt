package com.lindroid.lindialog_lib

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.lindroid.lindialog_lib.app.LinDialogApp

/**
 * @author Lin
 * @date 2019/2/2
 * @function Material提示对话框
 * @Description
 */
class HintDialog : DialogFragment() {
    private lateinit var fm: FragmentManager
    private lateinit var mContext: Context
    private var title = ""
    private var message = ""
    private var posText = ""
    private var negText = ""
    private var neuText = ""
    private var neuColor = 0
    private var negColor = 0
    private var posColor = 0
    private var showNeuButton = false
    private var showNegButton = true
    private var dismissable = true
    private var dialogTag = "HintDialog"
    private var posListener: ((DialogInterface) -> Unit)? = null
    private var negListener: ((DialogInterface) -> Unit)? = null
    private var neuListener: ((DialogInterface) -> Unit)? = null
    private var dismissListener: (() -> Unit)? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(posText, null)
            if (showNegButton) {
                setNegativeButton(negText, null)
            }
            if (showNeuButton) {
                setNeutralButton(neuText, null)
            }
        }
        return builder!!.create().apply {
            show()
            getButton(AlertDialog.BUTTON_POSITIVE).apply {
                setTextColor(if (posColor != 0) posColor else ContextCompat.getColor(context, R.color.hint_dialog_positive_text_color))
                setOnClickListener {
                    posListener?.invoke(dialog)
                    if (dismissable) {
                        dismiss()
                    }
                }
            }
            getButton(AlertDialog.BUTTON_NEGATIVE).apply {
                setTextColor(if (negColor != 0) negColor else ContextCompat.getColor(context, R.color.hint_dialog_negative_text_color))
                setOnClickListener {
                    negListener?.invoke(dialog)
                    if (dismissable) {
                        dismiss()
                    }
                }
            }
            getButton(AlertDialog.BUTTON_NEUTRAL).apply {
                setTextColor(neuColor)
                setOnClickListener {
                    neuListener?.invoke(dialog)
                    if (dismissable) {
                        dismiss()
                    }
                }
            }

        }
    }

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                HintDialog().apply {
                    this.fm = fm
                    mContext = LinDialogApp.instance
                    posText = mContext.getString(R.string.hint_dialog_positive_text)
                    negText = mContext.getString(R.string.hint_dialog_negative_text)
                    neuText = mContext.getString(R.string.hint_dialog_neutral_text)
//                    Log.e("Hint","context=$mContext")
                }
    }

    fun show() {
        this.show(fm, dialogTag)
    }

    fun setTitle(title: String) = this.apply { this.title = title }

    fun setMessage(msg: String) = this.apply { message = msg }

    fun setMessage(@StringRes id: Int) = setMessage(mContext.getString(id))

    fun setPositiveText(text: String) = this.apply { posText = text }

    fun setPositiveText(@StringRes id: Int) = setPositiveText(getString(id))

    fun setNegativeText(text: String) = this.apply { negText = text }

    fun setNegativeText(@StringRes id: Int) = setNegativeText(getString(id))

    fun setNeutralText(text: String) = this.apply { neuText = text }

    fun setNeutralText(@StringRes id: Int) = setNeutralText(getString(id))

    fun setShowNeuButton(showNeuBtn: Boolean) = this.apply { showNeuButton = showNeuBtn }

    fun setShowNegButton(showNegBtn: Boolean) = this.apply { showNegButton = showNegBtn }

    fun setTag(tag: String) = this.apply { dialogTag = tag }

    fun setCanDissmiss(dismissable: Boolean) = this.apply { this.dismissable = dismissable }

    fun setPosClickListener(listener: (DialogInterface) -> Unit) = this.apply { posListener = listener }

    fun setNegClickListener(listener: (DialogInterface) -> Unit) = this.apply { negListener = listener }

    fun setNeuClickListener(listener: (DialogInterface) -> Unit) = this.apply { neuListener = listener }

    fun setDismissListener(listener: () -> Unit) = this.apply { dismissListener = listener }

    fun setNeuTextColor(@ColorInt color: Int) = this.apply { neuColor = color }

    fun setNeuTextColorId(@ColorRes colorId: Int) =
            this.apply { setNeuTextColor(ContextCompat.getColor(mContext, colorId)) }

    fun setNegTextColor(@ColorInt color: Int) =
            this.apply { negColor = color }

    fun setNegTextColorId(@ColorRes colorId: Int) =
            this.apply { setNegTextColor(ContextCompat.getColor(mContext, colorId)) }

    fun setPosTextColor(@ColorInt color: Int) = this.apply { posColor = color }

    fun setPosTextColorId(@ColorRes colorId: Int) =
            this.apply { setPosTextColor(ContextCompat.getColor(mContext, colorId)) }

    /**
     * 点击对话框外部关闭对话框
     */
    fun setCanCancel(isCancelable: Boolean) = this.apply { this.isCancelable = isCancelable }

}