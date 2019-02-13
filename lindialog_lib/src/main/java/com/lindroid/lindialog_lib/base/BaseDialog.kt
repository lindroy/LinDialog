package com.lindroid.lindialog_lib.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.FloatRange
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*

/**
 * @author Lin
 * @date 2019/2/2
 * @function 常规对话框基类
 * @Description
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseDialog<T : BaseDialog<T>> : DialogFragment() {
    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    abstract var customViewId: Int

    private var layoutId: Int = 0

    lateinit var fm: FragmentManager

    private var viewHandler: ((ViewHolder, DialogInterface) -> Unit)? = null

    private var dismissListener: (() -> Unit)? = null

    private var dialogTag = "BottomDialog"

    private var contentView: View? = null

    private var animStyle = 0

    private var widthScale = 0F

    private var gravity: Int = Gravity.CENTER

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
        fun getScreenWidth(): Int {
            val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                true -> wm.defaultDisplay.getRealSize(point)
                false -> wm.defaultDisplay.getSize(point)
            }
            return point.x
        }
        dialog.window?.apply {
            val params = attributes
            params.gravity = gravity
            //去除白色的背景
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //设置窗体动画
            setWindowAnimations(animStyle)
            if (widthScale > 0) {
                params.width = (getScreenWidth() * widthScale).toInt()
            }
            attributes = params
        }
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

    fun setContentView(contentView: View) = this.apply { this.contentView = contentView } as T

    fun setContentView(@LayoutRes layoutId: Int) = this.apply { this.layoutId = layoutId } as T

    fun setTag(tag: String) = this.apply { dialogTag = tag } as T

    fun setAnimStyle(@StyleRes style: Int) = this.apply { this.animStyle = style } as T

    fun setWidthScale(@FloatRange(from = 0.0, to = 1.0) scale: Float) = this.apply { widthScale = scale } as T

    fun setGravity(gravity: Int) = this.apply { this.gravity = gravity } as T

    fun setViewHandler(viewHandler: (holder: ViewHolder, dialog: DialogInterface) -> Unit) =
            this.apply { this.viewHandler = viewHandler } as T

    fun setDismissListener(listener: () -> Unit) = this.apply { dismissListener = listener } as T

    /**
     * 点击对话框外部关闭对话框
     */
    fun setCancelOutSide(isCancelable: Boolean) = this.apply { this.isCancelable = isCancelable } as T

    override fun onDestroy() {
        super.onDestroy()
        viewHandler = null
        dismissListener = null
    }
}