package com.lindroid.iosdialog

import android.content.DialogInterface
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.lindroid.iosdialog.adapter.DialogListAdapter
import com.lindroid.iosdialog.bean.DialogItemBean
import com.lindroid.lindialog.LinDialog.getResColor
import com.lindroid.lindialog.base.BaseBottomDialog
import kotlinx.android.synthetic.main.dialog_bottom_sheet_ios.*

/**
 * @author Lin
 * @date 2019/5/18
 * @function iOS风格的底部菜单对话框
 * @Description
 */
class IBottomSheetDialog : BaseBottomDialog<IBottomSheetDialog>() {

    private var title: String = ""

    private var titleSize = IDialog.titleConfigs.textSize

    private var titleColor = IDialog.titleConfigs.textColor

    private var message: String = ""

    private var messageSize = IDialog.msgConfigs.textSize

    private var messageColor = IDialog.msgConfigs.textColor

    private var radius = IDialog.cornerRadius

    private var bgColor = IDialog.bgColor

    private var bgAlpha = IDialog.alpha

    private val items: MutableList<DialogItemBean> = ArrayList()

    private var itemClickListener: ((Int, String, TextView, DialogInterface) -> Unit)? = null


    companion object {
        @JvmStatic
        fun build(fm: FragmentManager) =
                IBottomSheetDialog().apply {
                    this.fm = fm
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
        setWidthScale(0.95F)
        setDraggable(false)
//        TODO(ShapeDrawable的宽高会跟随第一个设置background的View)
        llContent.background = initShapeDrawable()
        btnCancel.apply {
            text = IDialog.negButtonConfigs.text
            setTextColor(IDialog.negButtonConfigs.textColor)
            background = initShapeDrawable()
            setOnClickListener { dismiss() }
        }
        initListView()
        return true
    }

    fun addItem(text: String, @ColorInt textColor: Int = getResColor(R.color.lin_dialog_text_color_blue)) = this.apply {
        items.add(DialogItemBean(text, textColor))
    }

    private fun initListView() {
        lvChoices.apply {
            divider = ContextCompat.getDrawable(mContext, R.drawable.dialog_ios_divider)
            dividerHeight = resources.getDimensionPixelSize(R.dimen.lin_dialog_line_size)
            adapter = DialogListAdapter(mContext, R.layout.item_dialog_list, items)
            setOnItemClickListener { parent, view, position, id ->
                itemClickListener?.invoke(position, items[position].text, view as TextView, dialog)
            }
        }

    }

    private fun initShapeDrawable(): ShapeDrawable {
        val roundRectShape = RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
        return with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
    }

    /**
     * item的点击监听事件
     */
    fun setOnItemClickListener(listener: (position: Int, text: String, itemView: TextView, dialog: DialogInterface) -> Unit) =
            this.apply { itemClickListener = listener }

    override fun onDestroy() {
        super.onDestroy()
        itemClickListener = null
    }

}

