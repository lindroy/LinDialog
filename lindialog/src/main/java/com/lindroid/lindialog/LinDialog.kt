package com.lindroid.lindialog

import android.app.Application
import android.content.Context

/**
 * @author Lin
 * @date 2019/2/13
 * @function
 * @Description
 */
object LinDialog {

    private lateinit var application: Application
    fun init(application: Application) {
        this.application = application
    }

    val context: Context
        get() = application.applicationContext

}