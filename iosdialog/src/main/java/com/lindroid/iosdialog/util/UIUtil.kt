package com.lindroid.iosdialog.util

import com.lindroid.iosdialog.IDialog

/**
 * @author Lin
 * @date 2019/5/18
 * @function
 * @Description
 */
internal fun dp2px(dpValue: Float): Float {
    val scale = IDialog.context.resources.displayMetrics.density
    return (dpValue * scale + 0.5F)
}

internal fun px2sp(pxValue: Float): Float {
    val fontScale =  IDialog.context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5F)
}