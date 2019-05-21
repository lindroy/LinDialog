package com.lindroid.iosdialog

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.lindroid.iosdialog.bean.TextConfigs
import com.lindroid.iosdialog.util.dp2px
import com.lindroid.iosdialog.util.px2sp
import com.lindroid.lindialog.LinDialog

/**
 * @author Lin
 * @date 2019/5/18
 * @function IDialog配置类
 * @Description
 */
object IDialog {
    val context: Context
        get() = application.applicationContext

    internal var alertWidthScale = 0.7F

    internal var bottomWidthScale = 0.95F

    internal var cornerRadius = 0F

    internal var alpha = 0.85F

    internal var bgColor = Color.WHITE

    internal val alertTitleConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_title_size), textColorBlack)
    }

    internal val alertMsgConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_message_size), textColorBlack)
    }

    internal val alertPosBtnConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), textColorBlue, text = getString(R.string.ios_dialog_positive_text))
    }

    internal val alertNegBtnConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), textColorRed, text = getString(R.string.ios_dialog_negative_text))
    }

    internal val alertListItemConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_list_item_text_size), textColorBlue)
    }

    internal val bottomTitleConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_bottom_title_size), textColorBlack)
    }

    internal val bottomMsgConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_bottom_message_size), textColorBlack)
    }

    internal val bottomBtnConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_bottom_message_size), textColorBlue,
                text = context.getString(R.string.ios_dialog_negative_text))
    }

    internal val bottomListItemConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_bottom_list_item_text_size), textColorBlue)
    }

    private lateinit var application: Application

    private val textColorBlue by lazy {
        getResColor(R.color.ios_dialog_text_color_blue)
    }

    private val textColorRed by lazy {
        getResColor(R.color.ios_dialog_text_color_red)
    }

    private val textColorBlack by lazy {
        getResColor(R.color.ios_dialog_text_color_black)
    }

    fun init(application: Application): Config {
        this.application = application
        LinDialog.init(application)
        cornerRadius = dp2px(context.resources.getDimensionPixelSize(R.dimen.ios_dialog_corner_radius).toFloat())
        return Config.build()
    }

    internal fun getSpSize(dimenId: Int) = px2sp(context.resources.getDimensionPixelSize(dimenId).toFloat())

    internal fun getPxSize(dimenId: Int) = context.resources.getDimensionPixelSize(dimenId)

    internal fun getResColor(@ColorRes colorId: Int) = ContextCompat.getColor(context, colorId)

    internal fun getString(@StringRes stringId: Int) = context.getString(stringId)

    class Config {
        companion object {
            @JvmStatic
            fun build() = Config()
        }

        fun setCornerRadius(cornerRadius: Float) = this.apply {
            IDialog.cornerRadius = cornerRadius
        }

        //        @FloatRange(from = 0.0, to = 1.0)
        fun setAlpha(alpha: Float) = this.apply { IDialog.alpha = alpha }

        fun setBackgroudColor(@ColorInt color: Int) = this.apply { bgColor = color }

        fun setAlertWidthScale(widthScale: Float) = this.apply { alertWidthScale = widthScale }

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
        fun setAlertMsg(textSize: Float = alertMsgConfigs.textSize,
                        @ColorInt textColor: Int = alertMsgConfigs.textColor,
                        gravity: Int = alertMsgConfigs.gravity) = this.apply {
            alertMsgConfigs.let {
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setAlertPosButton(text: String = alertPosBtnConfigs.text,
                              textSize: Float = alertPosBtnConfigs.textSize,
                              @ColorInt textColor: Int = alertPosBtnConfigs.textColor,
                              gravity: Int = alertPosBtnConfigs.gravity
        ) = this.apply {
            alertPosBtnConfigs.let {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setAlertNegButton(text: String = alertNegBtnConfigs.text,
                              textSize: Float = alertNegBtnConfigs.textSize,
                              @ColorInt textColor: Int = alertNegBtnConfigs.textColor,
                              gravity: Int = alertNegBtnConfigs.gravity
        ) = this.apply {
            alertNegBtnConfigs.let {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setAlertListItem(textSize: Float = alertListItemConfigs.textSize,
                             @ColorInt textColor: Int = alertListItemConfigs.textColor,
                             height: Int = alertListItemConfigs.height
        ) = this.apply {
            alertListItemConfigs.let {
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
            }
        }

        @JvmOverloads
        fun setBottomListTitle(textSize: Float = bottomTitleConfigs.textSize,
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
        fun setBottomListMsg(textSize: Float = bottomMsgConfigs.textSize,
                             @ColorInt textColor: Int = bottomMsgConfigs.textColor,
                             gravity: Int = bottomMsgConfigs.gravity
        ) = this.apply {
            bottomMsgConfigs.let {
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setBottomListButton(textSize: Float = bottomBtnConfigs.textSize,
                                @ColorInt textColor: Int = bottomBtnConfigs.textColor,
                                text: String = bottomBtnConfigs.text,
                                height: Int = bottomBtnConfigs.height
        ) = this.apply {
            bottomBtnConfigs.let {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
            }
        }

        fun setBottomListItem(textSize: Float = bottomListItemConfigs.textSize,
                              @ColorInt textColor: Int = bottomListItemConfigs.textColor,
                              height: Int = bottomListItemConfigs.height
        ) = this.apply {
            bottomListItemConfigs.let {
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
            }
        }


        fun setBottomWidthScale(widthScale: Float) = this.apply { bottomWidthScale = widthScale }

    }
}