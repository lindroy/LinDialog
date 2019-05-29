package com.lindroid.lindialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.lindroid.lindialog.R
import com.lindroid.lindialog.bean.ListItemBean

/**
 * @author Lin
 * @date 2019/5/26
 * @function
 * @description
 */
class DialogListAdapter(private val mContext: Context, private val items: List<ListItemBean> ):
    ArrayAdapter<ListItemBean>(mContext, R.layout.item_list) {

    override fun getCount() = items.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItem(position: Int): ListItemBean? = items[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val vh: ViewHolder
        var itemView = convertView
        when (itemView) {
            null -> {
                vh = ViewHolder()
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null)
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


        }

        return itemView!!
    }

    class ViewHolder {
        lateinit var textView: TextView
    }
}