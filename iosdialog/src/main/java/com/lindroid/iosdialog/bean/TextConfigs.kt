package com.lindroid.iosdialog.bean

import android.support.annotation.ColorInt
import android.view.Gravity

/**
 * @author Lin
 * @date 2019/5/18
 * @function Dialog中的Text样式
 * @Description
 */
data class TextConfigs(
        var textSize: Float,
        @ColorInt var textColor: Int,
        var gravity: Int = Gravity.CENTER,
        var text: String = "",
        var isBold: Boolean = false,
        var height: Int = 0
)