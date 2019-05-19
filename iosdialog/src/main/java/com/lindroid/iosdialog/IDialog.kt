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

    internal var cornerRadius = 0F

    internal var alpha = 0.85F

    internal var bgColor = Color.WHITE

    internal lateinit var titleConfigs: TextConfigs

    internal lateinit var msgConfigs: TextConfigs

    internal lateinit var posButtonConfigs: TextConfigs

    internal lateinit var negButtonConfigs: TextConfigs

    fun init(application: Application): Config {
        this.application = application
        LinDialog.init(application)
        cornerRadius = dp2px(10F)
        titleConfigs = TextConfigs(18F, LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
        msgConfigs = TextConfigs(16F, LinDialog.getResColor(R.color.lin_dialog_text_color_black), Gravity.CENTER)
        posButtonConfigs = TextConfigs(16F, LinDialog.getResColor(R.color.lin_dialog_text_color_blue), Gravity.CENTER)
        negButtonConfigs = TextConfigs(16F, LinDialog.getResColor(R.color.lin_dialog_text_color_red), Gravity.CENTER)
        return Config.build()
    }

    val context: Context
        get() = application.applicationContext

    class Config {
        companion object {
            @JvmStatic
            fun build() = Config()
        }

        fun setAlertWidthScale(widthScale:Float) = this.apply { alertWidthScale = widthScale }

        fun setCornerRadius(cornerRadius: Float) = this.apply {
            IDialog.cornerRadius = cornerRadius
        }
//        @FloatRange(from = 0.0, to = 1.0)
        fun setAlpha( alpha: Float) = this.apply { IDialog.alpha = alpha }

        fun setBackgroudColor(@ColorInt color: Int) = this.apply { bgColor = color }

        @JvmOverloads
        fun setTitle(textSize: Float = px2sp(context.resources.getDimensionPixelSize(R.dimen.lin_dialog_title_text_size).toFloat()),
                     @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_black),
                     gravity: Int = Gravity.CENTER
        ) = this.apply {
            titleConfigs = TextConfigs(textSize, textColor, gravity)
        }

        fun setMessage(textSize: Float = px2sp(context.resources.getDimensionPixelSize(R.dimen.lin_dialog_message_text_size).toFloat()),
                       @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_black),
                       gravity: Int = Gravity.CENTER) = this.apply {
            msgConfigs = TextConfigs(textSize, textColor, gravity)
        }

        fun setPositiveButton(text: String = context.getString(R.string.ios_dialog_positive_text),
                              textSize: Float = 16F,
                              @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_blue),
                              gravity: Int = Gravity.CENTER
        ) = this.apply {
            posButtonConfigs = TextConfigs(textSize, textColor, gravity, text)
        }

        fun setNegativeButton(text: String = context.getString(R.string.ios_dialog_negative_text),
                              textSize: Float = px2sp(context.resources.getDimensionPixelSize(R.dimen.lin_dialog_button_text_size).toFloat()),
                              @ColorInt textColor: Int = LinDialog.getResColor(R.color.lin_dialog_text_color_red),
                              gravity: Int = Gravity.CENTER
        ) = this.apply {
            negButtonConfigs = TextConfigs(textSize, textColor, gravity, text)
        }
    }
}