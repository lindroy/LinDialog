package com.lindroid.lindialog.util

import com.lindroid.lindialog.LinDialog

/**
 * @author Lin
 * @date 2019/5/28
 * @function
 * @Description
 */
internal fun dp2px(dpValue: Float): Float {
    val scale = LinDialog.context.resources.displayMetrics.density
    return (dpValue * scale + 0.5F)
}

internal fun px2sp(pxValue: Float): Float {
    val fontScale =  LinDialog.context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5F)
}

internal fun getSpSize(dimenId: Int) = px2sp(LinDialog.context.resources.getDimensionPixelSize(dimenId).toFloat())

internal fun getPxSize(dimenId: Int) = LinDialog.context.resources.getDimensionPixelSize(dimenId)