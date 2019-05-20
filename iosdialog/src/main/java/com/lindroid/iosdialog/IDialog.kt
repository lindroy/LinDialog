package com.lindroid.iosdialog

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.view.Gravity
import com.lindroid.iosdialog.bean.TextConfigs
import com.lindroid.iosdialog.util.dp2px
import com.lindroid.iosdialog.util.px2sp
import com.lindroid.lindialog.LinDialog

/**
 * @author Lin
 * @date 2019/5/18
 * @function
 * @Description
 */
object IDialog {
    private lateinit var application: Application

    internal var alertWidthScale = 0.7F

    internal var bottomWidthScale = 0.95F

    internal var cornerRadius = 0F

    internal var alpha = 0.85F


    internal var bgColor = Color.WHITE

    internal lateinit var alertTitleConfigs: TextConfigs

    internal lateinit var alertMsgConfigs: TextConfigs

    internal lateinit var alertPosBtnConfigs: TextConfigs

    internal lateinit var alertNegBtnConfigs: TextConfigs

    internal lateinit var bottomTitleConfigs: TextConfigs

    internal lateinit var bottomMsgConfigs: TextConfigs

    val context: Context
        get() = application.applicationContext

    fun init(application: Application): Config {
        this.application = application
        LinDialog.init(application)
        cornerRadius = dp2px(10F)
        alertTitleConfigs = TextConfigs(getSpSize(R.dimen.ios_alert_title_size), LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
        alertMsgConfigs = TextConfigs(getSpSize(R.dimen.ios_alert_message_size), LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
        alertPosBtnConfigs = TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), LinDialog.getResColor(R.color.lin_dialog_text_color_blue), Gravity.CENTER)
        alertNegBtnConfigs = TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), LinDialog.getResColor(R.color.lin_dialog_text_color_red), Gravity.CENTER)
        bottomTitleConfigs = TextConfigs(getSpSize(R.dimen.ios_bottom_title_size), LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
        bottomMsgConfigs = TextConfigs(getSpSize(R.dimen.ios_bottom_message_size), LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
        return Config.build()
    }

    private fun getSpSize(dimenId: Int) = px2sp(context.resources.getDimensionPixelSize(dimenId).toFloat())


    class Config {
        companion object {
            @JvmStatic
            fun build() = Config()
        }

        fun setAlertWidthScale(widthScale: Float) = this.apply { alertWidthScale = widthScale }

        fun setCornerRadius(cornerRadius: Float) = this.apply {
            IDialog.cornerRadius = cornerRadius
        }

        //        @FloatRange(from = 0.0, to = 1.0)
        fun setAlpha(alpha: Float) = this.apply { IDialog.alpha = alpha }

        fun setBackgroudColor(@ColorInt color: Int) = this.apply { bgColor = color }

        @JvmOverloads
        fun setAlertTitle(textSize: Float = alertTitleConfigs.textSize,
                          @ColorInt textColor: Int = alertTitleConfigs.textColor,
                          gravity: Int = alertTitleConfigs.gravity
        ) = this.apply {
            alertTitleConfigs = TextConfigs(textSize, textColor, gravity)
        }

        @JvmOverloads
        fun setAlertMessage(textSize: Float = alertMsgConfigs.textSize,
                            @ColorInt textColor: Int = alertMsgConfigs.textColor,
                            gravity: Int = alertMsgConfigs.gravity) = this.apply {
            alertMsgConfigs = TextConfigs(textSize, textColor, gravity)
        }

        fun setAlertPosButton(text: String = context.getString(R.string.ios_dialog_positive_text),
                              textSize: Float = 16F,
                              @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_blue),
                              gravity: Int = Gravity.CENTER
        ) = this.apply {
            alertPosBtnConfigs = TextConfigs(textSize, textColor, gravity, text)
        }

        fun setAlertNegButton(text: String = context.getString(R.string.ios_dialog_negative_text),
                              textSize: Float = px2sp(context.resources.getDimensionPixelSize(R.dimen.ios_alert_button_text_size).toFloat()),
                              @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_red),
                              gravity: Int = Gravity.CENTER
        ) = this.apply {
            alertNegBtnConfigs = TextConfigs(textSize, textColor, gravity, text)
        }

        @JvmOverloads
        fun setBottomTitle(textSize: Float = bottomTitleConfigs.textSize,
                           @ColorInt textColor: Int = bottomTitleConfigs.textColor,
                           gravity: Int = bottomTitleConfigs.gravity
        ) = this.apply {
            bottomTitleConfigs = TextConfigs(textSize, textColor, gravity)
        }

        @JvmOverloads
        fun setBottomMessage(textSize: Float = bottomMsgConfigs.textSize,
                             @ColorInt textColor: Int = bottomMsgConfigs.textColor,
                             gravity: Int = bottomMsgConfigs.gravity
        ) = this.apply {
            bottomMsgConfigs = TextConfigs(textSize, textColor, gravity)
        }

        fun setBottomWidthScale(widthScale: Float) = this.apply { bottomWidthScale = widthScale }

    }
}