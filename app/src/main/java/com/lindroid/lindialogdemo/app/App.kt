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
                .setAlertButtonHeight(dp2px(100))
                .setAlertPosButton(text = "Ok")
                .setAlertNegButton(text = "Cancel")

        /*IDialog.init(instance) //初始化方法，必须！！！
                //以下方法是对所有对话框的配置
                .setBackgroudColor(Color.WHITE) //背景颜色
                .setCornerRadius(48F) //背景圆角半径（px）
                .setAlpha(0.85F) //背景透明度
                //以下方法用于配置提示类对话框（IAlert和IAlertList）
                .setAlertWidthScale(0.7F) //提示对话框与屏幕宽度比
                .setAlertAnimStyle(0) //动画样式
                .setAlertPaddingTop(80) //对话框顶部内边距（px）
                .setAlertPaddingBottom(80) //对话框标题与消息文字布局的底部内边距（px）
                .setAlertPaddingSides(60) //对话框标题与消息文字布局的两侧内边距（px）
                .setAlertPaddingTitleMsg(20) //对话框标题与消息文字之间的间距（px）
                .setAlertTitleView( //对话框标题样式
                        textSize = 16F, //字体大小（sp）
                        textColor = Color.BLACK,
                        text = "提示",//标题文字
                        gravity = Gravity.CENTER
                )
                .setAlertMsgView( //对话框消息文字样式
                        textSize = 14F, //字体大小（sp）
                        textColor = Color.BLACK,
                        gravity = Gravity.CENTER
                )
                .setAlertButtonHeight(200) //对话框按钮高度（px）
                .setAlertPosButton( //确认按钮样式
                        text = "OK",
                        textSize = 14F, //字体大小（sp）
                        textColor = Color.BLUE,
                        gravity = Gravity.CENTER
                )
                .setAlertNegButton( //取消按钮样式
                        text = "Cancel",
                        textSize = 14F, //字体大小（sp）
                        textColor = Color.RED,
                        gravity = Gravity.CENTER
                )
                //以下方法用于配置IAlertListDialog
                .setAlertListItem( //列表选项样式
                        textSize = 14F, //字体大小（sp）
                        textColor = Color.BLUE,
                        height = 200, //高度（px）
                        paddingSides = 60 //两侧的padding值（px）
                )
                //以下方法用于配置底部对话框（目前只有IBottomList）
                .setBottomAnimStyle(0) //动画样式
                .setBottomWidthScale(0.95F) //底部对话框与屏幕宽度之比
                .setBottomPaddingTop(80) //对话框顶部内边距（px）
                .setBottomPaddingBottom(80) //对话框标题与消息文字布局的底部内边距（px）
                .setBottomPaddingSides(60) //对话框标题与消息文字布局的两侧内边距（px）
                .setBottomPaddingTitleMsg(20) //对话框标题与消息文字之间的间距（px）
                //以下方法用于配置底部列表对话框（IBottomListDialog）
                .setBottomListTitleView( //对话框标题样式
                        textSize = 16F, //字体大小（sp）
                        textColor = Color.BLACK,
                        text = "提示",//标题文字
                        gravity = Gravity.CENTER
                )
                .setBottomListMsgView( //对话框说明文字样式
                        textSize = 14F, //字体大小（sp）
                        textColor = Color.BLACK,
                        gravity = Gravity.CENTER
                )
                .setBottomListButton(  //取消按钮样式
                        textSize = 16F, //字体大小（sp）
                        textColor = Color.BLACK,
                        text = "取消", //按钮文字
                        height = 200 //高度（px）
                )
                .setBottomListItem( //列表选项样式
                        textSize = 14F, //字体大小（sp）
                        textColor = Color.BLUE,
                        height = 200, //高度（px）
                        paddingSides = 60 //两侧的padding值（px）
                )*/
    }
}