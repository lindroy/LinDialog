package com.lindroid.iosdialog

import android.content.DialogInterface
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.lindroid.iosdialog.adapter.DialogListAdapter
import com.lindroid.iosdialog.base.BaseIOSDialog
import com.lindroid.iosdialog.bean.DialogItemBean
import kotlinx.android.synthetic.main.dialog_alert_list_ios.*

/**
 * @author Lin
 * @date 2019/5/19
 * @function 多选项的iOS风格提示对话框
 * @description
 */
class IAlertListDialog : BaseIOSDialog<IAlertListDialog>() {

    private val items: MutableList<DialogItemBean> = ArrayList()

    private var itemClickListener: ((Int, String, TextView, DialogInterface) -> Unit)? = null

    private val alertListBtnConfigs = IDialog.alertNegBtnConfigs

    private val alertListItemConfigs = IDialog.alertListItemConfigs

    override var customViewId: Int = R.layout.dialog_alert_list_ios

    private var dismissible = true

    private var itemDismissible = true

    private var cancelClickListener: ((DialogInterface) -> Unit)? = null

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                IAlertListDialog().apply {
                    this.fm = fm
                    titleConfig = IDialog.alertTitleConfigs
                    msgConfig = IDialog.alertMsgConfigs
                }
    }

    override fun onHandleView(contentView: View): Boolean {
        super.onHandleView(contentView)
        setAnimStyle(R.style.ScaleDialogStyle)
        llRoot.background = initShapeDrawable()
        initListView()
        btnAlertList.apply {
            alertListBtnConfigs.let {
                text = it.text
                textSize = it.textSize
                setTextColor(it.textColor)
            }
            setOnClickListener {
                cancelClickListener?.invoke(dialog)
                if (dismissible) {
                    dismiss()
                }
            }
        }
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

    @JvmOverloads
    fun addItem(text: String, @ColorInt textColor: Int = alertListItemConfigs.textColor, textSize: Float = alertListItemConfigs.textSize) = this.apply {
        items.add(DialogItemBean(text, textColor, textSize, alertListItemConfigs.height))
    }

    fun addItems(items: List<String>) = this.apply {
        items.forEach { addItem(it) }
    }

    /**
     * 设置列表Item高度
     */
    fun setItemHeight(height: Int) = this.apply { alertListItemConfigs.height = height }

    fun setItemHeightId(@DimenRes resId: Int) = this.apply { setItemHeight(IDialog.getPxSize(resId)) }

    /**
     * 点击取消按钮是否关闭对话框
     */
    fun setCanCelClickedDismissible(dismissible: Boolean) = this.apply { this.dismissible = dismissible }

    /**
     * 设置取消按钮文字
     */
    fun setCancelText(text: String) = this.apply { alertListBtnConfigs.text = text }

    /**
     * 设置取消按钮文字Id
     */
    fun setCancelText(@StringRes stringId: Int) = this.apply { setCancelText(IDialog.getString(stringId)) }

    /**
     * 设置取消按钮文字颜色
     */
    fun setCancelTextColor(@ColorInt color: Int) = this.apply { alertListBtnConfigs.textColor = color }

    /**
     * 设置取消按钮文字颜色Id
     */
    fun setCancelTextColorId(@ColorRes colorId: Int) = this.apply { setCancelTextColor(IDialog.getResColor(colorId)) }

    /**
     * 设置取消按钮文字大小，单位为sp
     */
    fun setCancelTextSize(textSize: Float) = this.apply { alertListBtnConfigs.textSize = textSize }

    /**
     * 设置取消按钮文字大小
     * @param dimens资源
     */
    fun setCancelTextSizeId(@DimenRes textSizeId: Int) = this.apply { setCancelTextSize(IDialog.getSpSize(textSizeId)) }

    /**
     * 设置取消按钮的样式和点击事件
     */
    fun setCancelButton(text: String = alertListBtnConfigs.text,
                        textColor: Int = alertListBtnConfigs.textColor,
                        textSize: Float = alertListBtnConfigs.textSize,
                        listener: (dialog: DialogInterface) -> Unit) = this.apply {
        alertListBtnConfigs.let {
            it.text = text
            it.textColor = textColor
            it.textSize = textSize
        }
        cancelClickListener = listener
    }

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
    fun setOnCancelClickListener(listener: (dialog: DialogInterface) -> Unit) = this.apply { cancelClickListener = listener }

    override fun onDestroy() {
        super.onDestroy()
        itemClickListener = null
        cancelClickListener = null
    }
}