package com.lindroid.lindialog

import android.app.Application
import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat

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

     fun getResColor(@ColorRes colorId:Int) = ContextCompat.getColor(context,colorId)



}
