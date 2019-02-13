package com.lindroid.lindialog.app

import android.app.Application
import com.lindroid.lindialog_lib.LinDialog
import com.squareup.leakcanary.LeakCanary

/**
 * @author Lin
 * @date 2019/2/12
 * @function
 * @Description
 */
class App : Application() {
    companion object {
        lateinit var instance: App

    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        LinDialog.init(instance)
    }
}