package com.lindroid.iosdialog.bean

import android.support.annotation.ColorInt

/**
 * @author Lin
 * @date 2019/5/18
 * @function Dialog中的Text样式
 * @Description
 */
data class TextConfigs(
        val textSize: Float,
        @ColorInt val textColor: Int,
        val  gravity: Int,
        val text:String = ""
)