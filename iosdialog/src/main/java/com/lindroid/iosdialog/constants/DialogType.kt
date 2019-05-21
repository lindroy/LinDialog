package com.lindroid.iosdialog.constants

import android.support.annotation.IntDef

/**
 * @author Lin
 * @date 2019/5/21
 * @function 对话框种类
 * @Description
 */
const val DIALOG_ALERT = 0x001
const val DIALOG_ALERT_LIST = 0x002
const val DIALOG_BOTTOM_LIST = 0x003

@IntDef(DIALOG_ALERT, DIALOG_ALERT_LIST, DIALOG_BOTTOM_LIST)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE)
@Retention(AnnotationRetention.SOURCE)
annotation class DialogType