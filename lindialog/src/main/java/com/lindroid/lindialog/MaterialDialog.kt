package com.lindroid.lindialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog

/**
 * @author Lin
 * @date 2019/2/2
 * @function Material风格提示对话框
 * @Description
 */
class MaterialDialog : DialogFragment() {
    private lateinit var fm: FragmentManager
    private lateinit var mContext: Context
    private var title = ""
    private var message = ""
    private var posText = "确定"
    private var negText = "取消"
    private var neuText = ""
    private var neuColor = 0
    private var negColor = 0
    private var posColor = 0
    private var showNeuButton = false
    private var showNegButton = true
    private var dismissible = true
    private var dialogTag = "MaterialDialog"
    private var themeStyle = 0
    private var animStyle = 0
    private var posListener: ((DialogInterface) -> Unit)? = null
    private var negListener: ((DialogInterface) -> Unit)? = null
    private var neuListener: ((DialogInterface) -> Unit)? = null
    private var dismissListener: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = context?.let { AlertDialog.Builder(it, themeStyle) }
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
                setTextColor(if (posColor != 0) posColor else ContextCompat.getColor(context, R.color.material_dialog_positive_text_color))
                setOnClickListener {
                    posListener?.invoke(dialog)
                    if (dismissible) {
                        dismiss()
                    }
                }
            }
            getButton(AlertDialog.BUTTON_NEGATIVE).apply {
                setTextColor(if (negColor != 0) negColor else ContextCompat.getColor(context, R.color.material_dialog_negative_text_color))
                setOnClickListener {
                    negListener?.invoke(dialog)
                    if (dismissible) {
                        dismiss()
                    }
                }
            }
            getButton(AlertDialog.BUTTON_NEUTRAL).apply {
                setTextColor(if (neuColor != 0) neuColor else ContextCompat.getColor(context, R.color.material_dialog_neutral_text_color))
                setOnClickListener {
                    neuListener?.invoke(dialog)
                    if (dismissible) {
                        dismiss()
                    }
                }
            }

        }
    }

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                MaterialDialog().apply {
                    this.fm = fm
                    mContext = LinDialog.context
                    posText = mContext.getString(R.string.material_dialog_positive_text)
                    negText = mContext.getString(R.string.material_dialog_negative_text)
                    neuText = mContext.getString(R.string.material_dialog_neutral_text)
                }
    }

    override fun onStart() {
        super.onStart()
        //设置对话框动画
        dialog.window?.setWindowAnimations(animStyle)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        dismissListener?.invoke()
    }

    /**
     * 显示对话框
     */
    fun show() {
        show(fm, dialogTag)
    }

    /**
     * 设置对话框标题
     * @param title:标题文字
     */
    fun setTitle(title: String) = this.apply { this.title = title }

    /**
     * @see setTitle(String)
     * @param id:标题文字Id
     */
    fun setTitle(@StringRes id: Int) = this.apply { setTitle(mContext.getString(id)) }

    /**
     * 设置对话框上的信息文字
     * @param msg:信息文字
     */
    fun setMessage(msg: String) = this.apply { message = msg }

    /**
     * @see setMessage(String)
     * @param id:信息文字Id
     */
    fun setMessage(@StringRes id: Int) = setMessage(mContext.getString(id))

    /**
     * 设置Positive按钮（即右侧的“确认”）文字
     */
    fun setPositiveText(text: String) = this.apply { posText = text }

    /**
     * @see setPositiveText(String)
     */
    fun setPositiveText(@StringRes id: Int) = setPositiveText(mContext.getString(id))

    /**
     * 设置Negative按钮（即中间的“取消”）文字
     */
    fun setNegativeText(text: String) = this.apply { negText = text }

    /**
     * 设置Positive按钮（即右侧的“确认”）文字
     */
    fun setNegativeText(@StringRes id: Int) = setNegativeText(mContext.getString(id))

    /**
     * 设置Neutral按钮（即最左侧的按钮）文字
     */
    fun setNeutralText(text: String) = this.apply { neuText = text }

    /**
     * @see setNeutralText(String)
     */
    fun setNeutralText(@StringRes id: Int) = setNeutralText(mContext.getString(id))

    /**
     * 设置Positive按钮的文字颜色
     * @param color:颜色值
     */
    fun setPosTextColor(@ColorInt color: Int) = this.apply { posColor = color }

    /**
     * 设置Positive按钮的文字颜色
     * @param colorId: 颜色资源Id
     * @see setPosTextColor(Int)
     */
    fun setPosTextColorId(@ColorRes colorId: Int) =
            this.apply { setPosTextColor(ContextCompat.getColor(mContext, colorId)) }

    /**
     * 设置Negative按钮的文字颜色
     * @param color:颜色值
     */
    fun setNegTextColor(@ColorInt color: Int) = this.apply { negColor = color }

    /**
     *
     * 设置Negative按钮的文字颜色
     * @param colorId:颜色资源Id
     * @see setNegTextColor
     */
    fun setNegTextColorId(@ColorRes colorId: Int) =
            this.apply { setNegTextColor(ContextCompat.getColor(mContext, colorId)) }

    /**
     * 设置Neutral按钮的文字颜色
     * @param color : 颜色值
     */
    fun setNeuTextColor(@ColorInt color: Int) = this.apply { neuColor = color }

    /**
     * 设置Neutral按钮的文字颜色
     * @param colorId:颜色资源Id
     */
    fun setNeuTextColorId(@ColorRes colorId: Int) =
            this.apply { setNeuTextColor(ContextCompat.getColor(mContext, colorId)) }

    /**
     * 是否显示Neutral按钮，默认为false，不显示
     */
    fun setShowNeuButton(showNeuBtn: Boolean) = this.apply { showNeuButton = showNeuBtn }

    /**
     * 是否显示Negative按钮，默认为true，显示
     */
    fun setShowNegButton(showNegBtn: Boolean) = this.apply { showNegButton = showNegBtn }

    /**
     * 设置Dialog的Tag，默认为MaterialDialog
     */
    fun setTag(tag: String) = this.apply { dialogTag = tag }

    /**
     * 点击对话框上的按钮是否可以关闭对话框，默认为true
     */
    fun setCanDismiss(dismissible: Boolean) = this.apply { this.dismissible = dismissible }

    /**
     * 设置对话框的主题样式
     */
    fun setThemeStyle(@StyleRes styleId: Int) = this.apply { themeStyle = styleId }

    /**
     * 设置对话框的动画样式
     */
    fun setAnimStyle(@StyleRes styleId: Int) = this.apply { animStyle = styleId }

    /**
     * 点击对话框外部关闭对话框
     */
    fun setCancelOutSide(isCancelable: Boolean) = this.apply { this.isCancelable = isCancelable }

    /**
     * Positive按钮的点击监听
     */
    fun setPosClickListener(listener: (DialogInterface) -> Unit) = this.apply { posListener = listener }

    /**
     * Negative按钮的点击监听
     */
    fun setNegClickListener(listener: (DialogInterface) -> Unit) = this.apply { negListener = listener }

    /**
     * Neutral按钮的点击监听
     */
    fun setNeuClickListener(listener: (DialogInterface) -> Unit) = this.apply { neuListener = listener }

    /**
     * 对话框关闭的监听
     */
    fun setDismissListener(listener: () -> Unit) = this.apply { dismissListener = listener }

    override fun onDestroy() {
        super.onDestroy()
        dismissListener = null
        posListener = null
        negListener = null
        neuListener = null
    }
}