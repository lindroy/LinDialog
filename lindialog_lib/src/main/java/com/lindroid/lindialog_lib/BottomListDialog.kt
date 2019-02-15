package com.lindroid.lindialog_lib

import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
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

    private var itemTextSize = 0F

    private var itemTextColor = 0

    private var itemPadding = arrayOf(0, 0, 0, 0)

    private var itemTextGravity = Gravity.CENTER

    private var background: Drawable? = null

    private var backgroundColorId: Int = 0

    private var divider: Drawable? = null

    private var dividerPadding: Int = 0

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
        if (divider != null) {
            llRoot.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            llRoot.dividerDrawable = divider
            llRoot.dividerPadding = dividerPadding
        }
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
                textSize = itemTextSize
                setTextColor(itemTextColor)
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

    /**
     * 在对话框中添加Item
     * @param name : item的文字
     */
    fun addItem(name: String) = this.apply { items.add(name) }

    /**
     * 在对话框中添加一组Item
     * @param items : item的文字数组
     */
    fun addItems(items: Array<String>) = this.apply { this.items.addAll(items.toList()) }

    /**
     * 设置对话框背景
     */
    fun setBackground(background: Drawable) = this.apply { this.background = background }

    /**
     * 设置对话框背景
     * @param resId:背景资源Id
     */
    fun setBackgroundResource(@DrawableRes resId: Int) = this.apply {
        ContextCompat.getDrawable(mContext, resId)?.let { setBackground(it) }
    }

    /**
     * 设置对话框背景颜色
     * @param colorId:颜色Id
     */
    fun setBackgroundColorId(@ColorRes colorId: Int) = this.apply { backgroundColorId = colorId }

    /**
     * 设置Item的高度，单位为px
     */
    fun setItemHeight(itemHeight: Int) = this.apply { this.itemHeight = itemHeight }

    /**
     * item的字体大小，单位为sp
     */
    fun setItemTextSize(itemTextSize: Float) = this.apply { this.itemTextSize = itemTextSize }

    /**
     * item的字体颜色
     */
    fun setItemTextColor(@ColorInt textColor: Int) = this.apply { itemTextColor = textColor }

    /**
     * item的字体颜色Id
     */
    fun setItemTextColorId(@ColorRes colorId: Int) = this.apply { setItemTextColor(ContextCompat.getColor(mContext, colorId)) }

    /**
     * item文字的位置
     */
    fun setItemTextGravity(gravity: Int) = this.apply { itemTextGravity = gravity }

    /**
     * item文字是否居中
     */
    fun setItemTextCenter() = this.apply { setItemTextGravity(Gravity.CENTER) }

    /**
     * 设置Item的padding值
     */
    fun setItemPadding(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) =
            this.apply { itemPadding = kotlin.arrayOf(left, top, right, bottom) }

    /**
     * 设置分割线
     * 可以创建一个shape文件，然后在里面设置分割线的颜色和高度
     */
    fun setDivider(divider: Drawable) = this.apply { this.divider = divider }

    /**
     * 设置分割线
     * 可以创建一个shape文件，然后在里面设置分割线的颜色和高度
     */
    fun setDivider(@DrawableRes resId: Int) = this.apply {
        ContextCompat.getDrawable(mContext, resId)?.let { setDivider(it) }
    }

    /**
     * 设置分割线左右的padding
     */
    fun setDividerPadding(padding: Int) = this.apply { dividerPadding = padding }

    /**
     * item的点击监听事件
     */
    fun setOnItemClickListener(listener: (position: Int, name: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
            this.apply { itemClickListener = listener }

    override fun onDestroy() {
        super.onDestroy()
        itemClickListener = null
    }
}