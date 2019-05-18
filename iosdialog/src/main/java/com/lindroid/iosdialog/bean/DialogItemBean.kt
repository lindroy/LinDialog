package com.lindroid.iosdialog.bean

import android.support.annotation.ColorInt

/**
 * @author Lin
 * @date 2019/5/18
 * @function IBottomSheetDialog的item实体类
 * @Description
 */
data class DialogItemBean(
        val text:String,
        @ColorInt val textColor: Int
)