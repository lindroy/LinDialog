package com.lindroid.iosdialog.base

import com.lindroid.iosdialog.IDialog

/**
 * @author Lin
 * @date 2019/5/22
 * @function iOS风格提示对话框基类
 * @Description
 */
abstract class BaseIAlertDialog<T : BaseIOSDialog<T>>  :BaseIOSDialog<T>(){

    init {
        titleConfig = IDialog.alertTitleConfigs.copy()
        msgConfig = IDialog.alertMsgConfigs.copy()
        paddingTitleMsg = IDialog.alertPaddingTitleMsg
        paddingTop = IDialog.alertPaddingTop
        paddingSides = IDialog.alertPaddingSides
        paddingBottom = IDialog.alertPaddingBottom
    }

}