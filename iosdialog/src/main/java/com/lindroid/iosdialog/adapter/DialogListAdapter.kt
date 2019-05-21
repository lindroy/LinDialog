package com.lindroid.iosdialog.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.lindroid.iosdialog.IDialog
import com.lindroid.iosdialog.R
import com.lindroid.iosdialog.bean.DialogItemBean
import com.lindroid.iosdialog.constants.DIALOG_ALERT_LIST
import com.lindroid.iosdialog.constants.DIALOG_BOTTOM_LIST
import com.lindroid.iosdialog.constants.DialogType

/**
 * @author Lin
 * @date 2019/5/18
 * @function iOS对话框中的列表适配器
 * @Description
 */
class DialogListAdapter(private val mContext: Context, @DialogType val dialogType: Int, @LayoutRes val layoutId: Int, private val items: List<DialogItemBean>) : ArrayAdapter<DialogItemBean>(mContext, layoutId) {
    override fun getCount() = items.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItem(position: Int): DialogItemBean? = items[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vh: ViewHolder
        var itemView = convertView
        when (itemView) {
            null -> {
                vh = ViewHolder()
                itemView = LayoutInflater.from(mContext).inflate(layoutId, null)
                vh.textView = itemView.findViewById(R.id.tvChoice)
                itemView.tag = vh
            }
            else -> vh = itemView.tag as ViewHolder
        }
        val item = items[position]
        vh.textView.also {
            it.text = item.text
            it.textSize = item.textSize
            it.setTextColor(item.textColor)
            when (dialogType) {
                DIALOG_ALERT_LIST -> IDialog.alertListItemConfigs
                DIALOG_BOTTOM_LIST -> IDialog.bottomListItemConfigs
                else -> IDialog.alertListItemConfigs
            }.apply {
                if (paddingLeft > 0) {
                    it.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
                }
                if (height > 0) {
                    it.height = height
                }
            }

        }

        return itemView!!
    }

    class ViewHolder {
        lateinit var textView: TextView
    }

}