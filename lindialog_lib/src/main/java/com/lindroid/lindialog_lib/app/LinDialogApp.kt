package com.lindroid.lindialog_lib.app

import android.app.Application

/**
 * @author Lin
 * @date 2019/2/2
 * @function
 * @Description
 */
class LinDialogApp : Application() {
    companion object {
        lateinit var instance: LinDialogApp
    }

    init {
        instance = this
    }
}