package com.lindroid.lindialog.base

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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

    private var widthScale = 0F

    private var isDraggable = true

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
        //去除白色的背景
        dialog.window?.apply {
            findViewById<View>(R.id.design_bottom_sheet)?.background = ColorDrawable(Color.TRANSPARENT)
            if (widthScale > 0) {
                val params = attributes
                params.width = (getScreenWidth() * widthScale).toInt()
                attributes = params
            }
        }
        //设置显示全部高度
        view?.post {
            val params = (view!!.parent as View).layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior
            val bottomSheetBehavior = behavior as BottomSheetBehavior
            if (!isDraggable){
                val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(p0: View, p1: Float) {
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        //禁止拖拽
                        if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }

                }
                bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback)
            }
            bottomSheetBehavior.peekHeight = view!!.measuredHeight
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
    fun setCancelOutside(isCancelable: Boolean) = this.apply { this.isCancelable = isCancelable } as T

    /**
     * 设置宽度与屏幕宽度比例
     * @param scale : 范围为0~1.0，为1时占满宽度
     *
     */
    fun setWidthScale(scale: Float) = this.apply { widthScale = scale } as T

    /**
     * 是否可以拖拽
     */
    fun setDraggable(isDraggable: Boolean) = this.apply { this.isDraggable = isDraggable }

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