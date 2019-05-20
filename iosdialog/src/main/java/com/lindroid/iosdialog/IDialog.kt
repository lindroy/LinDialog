package com.lindroid.iosdialog

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
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

    internal val alertTitleConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_title_size), LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
    }

    internal val alertMsgConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_message_size), LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
    }

    internal val alertPosBtnConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), LinDialog.getResColor(R.color.lin_dialog_text_color_blue), Gravity.CENTER)
    }

    internal val alertNegBtnConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), LinDialog.getResColor(R.color.lin_dialog_text_color_red), Gravity.CENTER)
    }

    internal val bottomTitleConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_bottom_title_size), getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
    }

    internal val bottomMsgConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_bottom_message_size), getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
    }

    val context: Context
        get() = application.applicationContext

    fun init(application: Application): Config {
        this.application = application
        LinDialog.init(application)
        cornerRadius = dp2px(context.resources.getDimensionPixelSize(R.dimen.ios_dialog_corner_radius).toFloat())
        return Config.build()
    }

    private fun getSpSize(dimenId: Int) = px2sp(context.resources.getDimensionPixelSize(dimenId).toFloat())

    fun getResColor(@ColorRes colorId: Int) = ContextCompat.getColor(context, colorId)


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
                          text: String = "",
                          gravity: Int = alertTitleConfigs.gravity
        ) = this.apply {
            alertTitleConfigs.let {
                it.textSize = textSize
                it.textColor = textColor
                it.text = text
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setAlertMessage(textSize: Float = alertMsgConfigs.textSize,
                            @ColorInt textColor: Int = alertMsgConfigs.textColor,
                            gravity: Int = alertMsgConfigs.gravity) = this.apply {
            alertMsgConfigs.let {
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        fun setAlertPosButton(text: String = context.getString(R.string.ios_dialog_positive_text),
                              textSize: Float = 16F,
                              @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_blue),
                              gravity: Int = Gravity.CENTER
        ) = this.apply {
            alertPosBtnConfigs.let {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        fun setAlertNegButton(text: String = context.getString(R.string.ios_dialog_negative_text),
                              textSize: Float = px2sp(context.resources.getDimensionPixelSize(R.dimen.ios_alert_button_text_size).toFloat()),
                              @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_red),
                              gravity: Int = Gravity.CENTER
        ) = this.apply {
            alertNegBtnConfigs.let {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setBottomTitle(textSize: Float = bottomTitleConfigs.textSize,
                           @ColorInt textColor: Int = bottomTitleConfigs.textColor,
                           text: String = "",
                           gravity: Int = bottomTitleConfigs.gravity
        ) = this.apply {
            bottomTitleConfigs.let {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setBottomMessage(textSize: Float = bottomMsgConfigs.textSize,
                             @ColorInt textColor: Int = bottomMsgConfigs.textColor,
                             gravity: Int = bottomMsgConfigs.gravity
        ) = this.apply {
            bottomMsgConfigs.let {
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        fun setBottomWidthScale(widthScale: Float) = this.apply { bottomWidthScale = widthScale }

    }
}