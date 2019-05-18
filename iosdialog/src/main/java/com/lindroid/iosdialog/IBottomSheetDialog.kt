package com.lindroid.iosdialog

import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.v4.app.FragmentManager
import android.view.View
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

    private val bgDrawable: ShapeDrawable by lazy {
        val roundRectShape = RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
        with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
    }
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
        tvTitle.apply {
//            text = title
            setTextColor(titleColor)
            textSize = titleSize
        }
        tvMessage.apply {
//            text = message
            setTextColor(messageColor)
            textSize = messageSize
        }
//        TODO(ShapeDrawable的宽高会跟随第一个设置background的View)
        llContent.background = initShapeDrawable()
        btnCancel.apply {
            text = IDialog.negButtonConfigs.text
            setTextColor(IDialog.negButtonConfigs.textColor)
            background = initShapeDrawable()
            setOnClickListener { dismiss() }
        }
        return true
    }

    private fun initShapeDrawable():ShapeDrawable{
        val roundRectShape = RoundRectShape(floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius), null, null)
       return with(ShapeDrawable(roundRectShape)) {
            paint.color = bgColor
            paint.style = Paint.Style.FILL
            paint.alpha = (255 * bgAlpha).toInt()
            this
        }
    }

}