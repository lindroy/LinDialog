package com.lindroid.lindialog_lib

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.lindroid.lindialog_lib.base.BaseBottomDialog
import kotlinx.android.synthetic.main.dialog_bottom_list.*

/**
 * @author Lin
 * @date 2019/2/14
 * @function 底部列表对话框
 * @Description
 */
class BottomListDialog : BaseBottomDialog<BottomListDialog>() {
    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    override var customViewId: Int = R.layout.dialog_bottom_list

    private var itemHeight = 200

    private var itemPadding = arrayOf(0, 0, 0, 0)

    private var itemTextGravity = Gravity.CENTER

    private var background: Drawable? = null

//    private var backgroundResId: Int = 0

    private var backgroundColorId: Int = 0

    private var items = ArrayList<String>()

    private var itemClickListener: ((Int, String, TextView, DialogInterface) -> Unit)? = null


    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                BottomListDialog().apply {
                    this.fm = fm
                }
    }

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        llRoot.dividerDrawable
        when {
            background != null -> llRoot.background = background
            backgroundColorId != 0 -> llRoot.setBackgroundColor(ContextCompat.getColor(mContext, backgroundColorId))
            else -> llRoot.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.white))
        }
        items.forEachIndexed { i, item ->
            val textView = with(TextView(context)) {
                text = item
                height = itemHeight
                gravity = itemTextGravity
                textSize = 16F
                setPadding(itemPadding[0], itemPadding[1], itemPadding[2], itemPadding[3])
                setOnClickListener {
                    itemClickListener?.invoke(i, item, this, dialog)
                }
                isClickable = true
                val attributes = intArrayOf(android.R.attr.selectableItemBackground)
                val typeValue = context.theme.obtainStyledAttributes(TypedValue().resourceId, attributes)
                background = typeValue.getDrawable(0)
                this
            }
            llRoot.addView(textView)
        }
        return true
    }

    fun addItem(name: String) = this.apply { items.add(name) }

    fun addItems(items: Array<String>) = this.apply { this.items.addAll(items.toList()) }

    fun setBackground(background: Drawable) = this.apply { this.background = background }

    fun setBackgroundResource(@DrawableRes resId: Int) = this.apply {
        if (ContextCompat.getDrawable(mContext, resId) != null) {
            setBackground(ContextCompat.getDrawable(mContext, resId)!!)
        }
    }

    fun setBackgroundColorId(@ColorRes colorId: Int) = this.apply { backgroundColorId = colorId }

    /**
     * 设置Item的高度，单位为px
     */
    fun setItemHeight(itemHeight: Int) = this.apply { this.itemHeight = itemHeight }

    fun setItemTextGravity(gravity: Int) = this.apply { itemTextGravity = gravity }

    fun setItemPadding(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) =
            this.apply { itemPadding = kotlin.arrayOf(left, top, right, bottom) }

    fun setOnItemClickListener(listener: (position: Int, name: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
            this.apply { itemClickListener = listener }
}