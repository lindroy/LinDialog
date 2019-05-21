package com.lindroid.lindialogdemo.app

import android.app.Application
import com.lindroid.androidutilskt.extension.dp2px
import com.lindroid.iosdialog.IDialog
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
//        LinDialog.init(instance)
        IDialog.init(instance)
//                .setCornerRadius(dp2px(12F))
                .setBottomListItem(paddingSides = dp2px(30))
                .setAlertPosButton(text = "Ok")
                .setAlertNegButton(text = "Cancel")
    }
}