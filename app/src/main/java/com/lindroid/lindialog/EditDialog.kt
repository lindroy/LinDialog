package com.lindroid.lindialog

import android.content.DialogInterface
import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import com.lindroid.lindialog.app.App
import com.lindroid.lindialog_lib.base.BaseDialog
import com.lindroid.utils.hideKeyboard
import com.lindroid.utils.shortToast
import com.lindroid.utils.showKeyboard
import kotlinx.android.synthetic.main.dialog_edit.*

/**
 * @author Lin
 * @date 2019/2/13
 * @function 输入框
 * @Description
 */
class EditDialog : BaseDialog<EditDialog>() {

    /**按鈕文字**/
    private var btnStr = ""
    /**提示文字**/
    private var hintStr = ""
    /**最大輸入字數，-1表示不设置**/
    private var maxLength = -1
    /**最小輸入字數，0表示不设置**/
    private var minLength = 0

    /**超过最大输入字数时的提示**/
    private var maxToastStr = ""

    /**字数不足时的提示**/
    private var minToastStr = ""

    /**EditText初始显示的文字**/
    private var content = ""

    private var dismissible = true

    private var finishListener: ((String, DialogInterface) -> Unit)? = null

    private var changeListener: ((String, Int) -> Unit)? = null

    override var customViewId = R.layout.dialog_edit

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        setGravity(Gravity.BOTTOM)
        setWidthScale(1.0F)
        btnStr = if (btnStr.isNotEmpty()) btnStr else "发送"
        //布局绘制完成时弹出软键盘
        etContent.hint = if (hintStr.isNotEmpty()) hintStr else "我来说两句~~"
        etContent.setText(content)
        etContent.setSelection(content.length)
        etContent.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (etContent.showKeyboard()) {
                    etContent.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }

            }
        })
        if (maxLength > 0) {
            tvLength.text = "(0/$maxLength)"
            maxToastStr = if (maxToastStr.isNotEmpty()) maxToastStr
            else "请不要超过${maxLength}个字哦"
        }
        etContent.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var str = s.toString()
                if (maxLength > 0) {
                    if (s.toString().length > maxLength) {
                        str = s.toString().substring(0, maxLength)
                        etContent.setText(str)
                        //光标移至最末端
                        etContent.setSelection(str.length)
                        context?.shortToast(maxToastStr)
                    }
                    tvLength.text = "(${str.length}/$maxLength)"
                }

                changeListener?.invoke(str, str.length)


            }

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
        tvFinish.text = btnStr
        tvFinish.setOnClickListener {
            if (minLength > 0 && etContent.text.toString().trim().length < minLength) {
                minToastStr = if (minToastStr.isEmpty()) "字数不能少于${minLength}个字" else minToastStr
                context?.shortToast(minToastStr)
                return@setOnClickListener
            }
            etContent.hideKeyboard()
            finishListener?.invoke(etContent.text.toString(), dialog)
            if (dismissible) {
                dismiss()
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                EditDialog().apply {
                    this.fm = fm
                }
    }

    fun setButtonText(text: String) = this.apply { btnStr = text }

    fun setButtonText(@StringRes id: Int) = setButtonText(App.instance.getString(id))

    fun setHint(text: String) = this.apply { hintStr = text }

    fun setHint(@StringRes id: Int) = setHint(App.instance.getString(id))

    fun setMaxLength(length: Int) = this.apply { maxLength = length }

    fun setMinLength(length: Int) = this.apply { minLength = length }

    fun setContent(text: String) = this.apply { content = text }

    fun setMaxToast(text: String) = this.apply { maxToastStr = text }

    fun setMinToast(text: String) = this.apply { minToastStr = text }

    fun setDismissible(dismissible: Boolean) = this.apply { this.dismissible = dismissible }

    /**
     * 点击完成按钮的监听事件
     */
    fun setFinishListener(listener: (content: String, dialog: DialogInterface) -> Unit) = this.apply { finishListener = listener }

    /**
     * 输入字符改变的监听事件
     */
    fun setTextChangeListener(listener: (content: String, length: Int) -> Unit) = this.apply { changeListener = listener }
}