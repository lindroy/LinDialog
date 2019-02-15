package com.lindroid.lindialog

import android.support.v4.app.FragmentManager
import android.view.View
import com.lindroid.lindialog.base.BaseBottomDialog

/**
 * @author Lin
 * @date 2019/2/13
 * @function 底部对话框
 * @Description
 */
class BottomDialog : BaseBottomDialog<BottomDialog>() {

    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    override var customViewId: Int = 0

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean = false

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                BottomDialog().apply {
                    this.fm = fm
                }
    }
}