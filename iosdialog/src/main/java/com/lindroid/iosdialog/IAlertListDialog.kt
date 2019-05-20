package com.lindroid.iosdialog

import android.content.DialogInterface
import android.support.annotation.ColorInt
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.lindroid.iosdialog.adapter.DialogListAdapter
import com.lindroid.iosdialog.base.BaseIOSDialog
import com.lindroid.iosdialog.bean.DialogItemBean
import com.lindroid.lindialog.LinDialog
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

    override var customViewId: Int = R.layout.dialog_alert_list_ios

    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
            IAlertListDialog().apply {
                this.fm = fm
            }
    }

    override fun onHandleView(contentView: View): Boolean {
        super.onHandleView(contentView)
        setAnimStyle(R.style.ScaleDialogStyle)
        llRoot.background = initShapeDrawable()
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
            }
        }
    }

    fun addItem(text: String, @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_blue)) = this.apply {
        items.add(DialogItemBean(text, textColor))
    }

    /**
     * item的点击事件
     */
    fun setOnItemClickListener(listener: (position: Int, text: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
        this.apply { itemClickListener = listener }

    override fun onDestroy() {
        super.onDestroy()
        itemClickListener = null
    }
}