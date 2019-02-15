package com.lindroid.lindialog.base

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lindroid.lindialog.LinDialog
import com.lindroid.lindialog.R

/**
 * @author Lin
 * @date 2019/2/13
 * @function 底部对话框基类
 * @Description
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseBottomDialog<T : BaseBottomDialog<T>> : BottomSheetDialogFragment() {
    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    abstract var customViewId: Int

    protected val mContext = LinDialog.context

    private var layoutId: Int = 0

    lateinit var fm: FragmentManager

    private var viewHandler: ((ViewHolder, DialogInterface) -> Unit)? = null

    private var dismissListener: (() -> Unit)? = null

    private var dialogTag = "BaseBottomDialog"

    private var contentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return when {
            customViewId > 0 -> inflater.inflate(customViewId, container, false)
            layoutId > 0 -> inflater.inflate(layoutId, container, false)
            contentView != null -> contentView
            else -> {
                throw IllegalStateException("请为对话框设置布局!!!")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!onHandleView(view)) {
            viewHandler?.invoke(ViewHolder(view), dialog)
        }
    }


    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    abstract fun onHandleView(contentView: View): Boolean

    override fun onStart() {
        super.onStart()
        //去除白色的背景
        dialog.window?.findViewById<View>(R.id.design_bottom_sheet)?.background = ColorDrawable(Color.TRANSPARENT)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        dismissListener?.invoke()
    }

    /**
     * 显示对话框
     */
    fun show() {
        this.show(fm, dialogTag)
    }

    /**
     * 设置自定义的布局
     */
    fun setContentView(contentView: View) = this.apply { this.contentView = contentView } as T

    /**
     * 设置自定义的布局Id
     */
    fun setContentView(@LayoutRes layoutId: Int) = this.apply { this.layoutId = layoutId } as T

    /**
     * 设置DialogFragment的Tag，默认为“BaseBottomDialog”
     */
    fun setTag(tag: String) = this.apply { dialogTag = tag } as T

    /**
     * 处理对话框中的View
     */
    fun setViewHandler(viewHandler: (holder: ViewHolder, dialog: DialogInterface) -> Unit) =
            this.apply { this.viewHandler = viewHandler } as T

    /**
     * 点击对话框外部关闭对话框
     */
    fun setCancelOutSide(isCancelable: Boolean) = this.apply { this.isCancelable = isCancelable } as T

    /**
     * 对话框消失监听
     */
    fun setDismissListener(listener: () -> Unit) = this.apply { dismissListener = listener } as T

    override fun onDestroy() {
        super.onDestroy()
        dismissListener = null
        viewHandler = null
    }
}