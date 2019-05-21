package com.lindroid.iosdialog

import android.content.DialogInterface
import android.support.annotation.ColorInt
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.lindroid.iosdialog.adapter.DialogListAdapter
import com.lindroid.iosdialog.base.BaseIOSDialog
import com.lindroid.iosdialog.bean.DialogItemBean
import com.lindroid.iosdialog.bean.TextConfigs
import kotlinx.android.synthetic.main.dialog_bottom_sheet_ios.*

/**
 * @author Lin
 * @date 2019/5/18
 * @function iOS风格的底部菜单对话框
 * @Description
 */
class IBottomSheetDialog : BaseIOSDialog<IBottomSheetDialog>() {

    private val items: MutableList<DialogItemBean> = ArrayList()

    private val bottomBtnConfig: TextConfigs = IDialog.bottomBtnConfigs

    private var itemClickListener: ((Int, String, TextView, DialogInterface) -> Unit)? = null

    private var cancelClickListener:((DialogInterface) -> Unit)? = null

    private var dismissible = true

    private var itemDismissible = true

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                IBottomSheetDialog().apply {
                    this.fm = fm
                    titleConfig = IDialog.bottomTitleConfigs
                    msgConfig = IDialog.bottomMsgConfigs
                }
    }

    /**
     * 子类继承BaseBottomDialog后需要创建的布局Id
     */
    override var customViewId = R.layout.dialog_bottom_sheet_ios

    /**
     * 返回true表示子类自己处理布局，setViewHandler方法无效
     */
    override fun onHandleView(contentView: View): Boolean {
        super.onHandleView(contentView)
        setGravity(Gravity.BOTTOM)
        setWidthScale(IDialog.bottomWidthScale)
        setAnimStyle(R.style.BottomDialogStyle)
//        TODO(ShapeDrawable的宽高会跟随第一个设置background的View)
        llContent.background = initShapeDrawable()

        btnCancel.apply {
            text = bottomBtnConfig.text
            textSize = bottomBtnConfig.textSize
            setTextColor(bottomBtnConfig.textColor)
            background = initShapeDrawable()
            setOnClickListener {
                cancelClickListener?.invoke(dialog)
                if (dismissible) {
                    dismiss()
                }
            }
        }
        initListView()
        return true
    }

    private fun initListView() {
        lvChoices.apply {
            divider = ContextCompat.getDrawable(mContext, R.drawable.dialog_ios_divider)
            dividerHeight = resources.getDimensionPixelSize(R.dimen.ios_dialog_divider_size)
            adapter = DialogListAdapter(mContext, R.layout.item_dialog_list, items)
            setOnItemClickListener { parent, view, position, id ->
                itemClickListener?.invoke(position, items[position].text, view as TextView, dialog)
                if (itemDismissible) {
                    dismiss()
                }
            }
        }
    }

    fun addItem(text: String, @ColorInt textColor: Int = IDialog.getResColor(R.color.ios_dialog_text_color_blue)) = this.apply {
        items.add(DialogItemBean(text, textColor))
    }

    fun addItems(items: Array<String>) = this.apply {
        items.forEach {
            addItem(it)
        }
    }

    /**
     * 点击取消按钮是否关闭对话框
     */
    fun setCanCelClickedDismissible(dismissible: Boolean) = this.apply { this.dismissible = dismissible }

    /**
     * 点击列表选项后是否关闭对话框
     */
    fun setItemClickedDismissible(itemDismissible: Boolean) = this.apply { this.itemDismissible = itemDismissible }

    /**
     * item的点击事件
     */
    fun setOnItemClickListener(listener: (position: Int, text: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
            this.apply { itemClickListener = listener }

    /**
     * 取消按钮点击事件
     */
    fun setOnCancelClickListener(listener:(dialog:DialogInterface) -> Unit) = this.apply { cancelClickListener = listener }

    override fun onDestroy() {
        super.onDestroy()
        itemClickListener = null
    }

}

