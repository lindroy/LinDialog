package com.lindroid.lindialogdemo

import android.content.DialogInterface
import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroid.lindialog.R
import com.lindroid.lindialog.base.BaseBottomDialog
import kotlinx.android.synthetic.main.dialog_pay.*

/**
 * @author Lin
 * @date 2019/2/13
 * @function 自定义的支付对话框
 * @Description 通过继承BaseBottomDialog扩展
 */
class PayDialog : BaseBottomDialog<PayDialog>() {

    private var aliPayListener: ((DialogInterface) -> Unit)? = null

    private var weChatListener: ((DialogInterface) -> Unit)? = null

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                PayDialog().apply {
                    this.fm = fm
                }
    }

    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    override var customViewId: Int = R.layout.dialog_pay


    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        tvAliPay.setOnClickListener {
            aliPayListener?.invoke(dialog)
        }
        tvWeChat.setOnClickListener {
            weChatListener?.invoke(dialog)
        }
        tvCancel.setOnClickListener { dismiss() }
        return true
    }

    fun setAliPayListener(listener: (dialog: DialogInterface) -> Unit) = this.apply { aliPayListener = listener }

    fun setWeChatListener(listener: (dialog: DialogInterface) -> Unit) = this.apply { weChatListener = listener }

    override fun onDestroy() {
        super.onDestroy()
        aliPayListener = null
        weChatListener = null
    }
}