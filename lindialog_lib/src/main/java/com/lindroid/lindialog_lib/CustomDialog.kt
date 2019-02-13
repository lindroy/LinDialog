package com.lindroid.lindialog_lib

import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroid.lindialog_lib.base.BaseDialog

/**
 * @author Lin
 * @date 2019/1/30
 * @function 自定义布局对话框
 * @Description
 */
class CustomDialog : BaseDialog<CustomDialog>() {
    override var customViewId: Int = 0

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean = false

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                CustomDialog().apply {
                    this.fm = fm
                }
    }
}