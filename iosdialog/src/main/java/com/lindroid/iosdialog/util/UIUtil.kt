package com.lindroid.iosdialog.util

import com.lindroid.lindialog.LinDialog

/**
 * @author Lin
 * @date 2019/5/18
 * @function
 * @Description
 */
internal fun dp2px(dpValue: Float): Float {
    val scale = LinDialog.context.resources.displayMetrics.density
    return (dpValue * scale + 0.5F)
}