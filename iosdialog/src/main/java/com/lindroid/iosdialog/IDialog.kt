package com.lindroid.iosdialog

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.StyleRes
import com.lindroid.iosdialog.bean.TextConfigs
import com.lindroid.iosdialog.util.getPxSize
import com.lindroid.iosdialog.util.getResColor
import com.lindroid.iosdialog.util.getResString
import com.lindroid.iosdialog.util.getSpSize
import com.lindroid.lindialog.LinDialog

/**
 * @author Lin
 * @date 2019/5/18
 * @function IDialog配置类
 * @Description
 */
object IDialog {

    internal val context: Context
        get() = application.applicationContext

    internal var alertWidthScale = 0.7F

    internal var bottomWidthScale = 0.95F

    internal var cornerRadius = 0F

    internal var alpha = 0.85F

    internal var bgColor = Color.WHITE

    internal var alertBtnHeight = 0

    internal val alertTitleConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_title_size), textColorBlack)
    }

    internal val alertMsgConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_message_size), textColorBlack)
    }

    /**
     * 提示类对话框动画
     */
    internal var alertAnimStyle: Int = R.style.AlertDialogStyle

    internal val alertPosBtnConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), textColorBlue, text = getResString(R.string.ios_dialog_positive_text))
    }

    internal val alertNegBtnConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_button_text_size), textColorRed, text = getResString(R.string.ios_dialog_negative_text))
    }

    internal val alertListItemConfigs by lazy {
        TextConfigs(getSpSize(R.dimen.ios_alert_list_item_text_size), textColorBlue)
    }

    internal var alertPaddingTop = 0

    internal var alertPaddingBottom = 0

    internal var alertPaddingSides = 0

    /**
     * 提示对话框标题与信息文字的间距
     */
    internal var alertPaddingTitleMsg = 0

    internal var bottomPaddingTop = 0

    internal var bottomPaddingBottom = 0

    internal var bottomPaddingSides = 0

    internal var bottomPaddingTitleMsg = 0

    internal var bottomAnimStyle: Int = R.style.BottomDialogStyle

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
        cornerRadius = getPxSize(R.dimen.ios_dialog_corner_radius).toFloat()
        alertPaddingTop = getPxSize(R.dimen.ios_alert_padding_top)
        alertPaddingBottom = getPxSize(R.dimen.ios_alert_padding_bottom)
        alertPaddingSides = getPxSize(R.dimen.ios_alert_padding_sides)
        alertPaddingTitleMsg = getPxSize(R.dimen.ios_alert_padding_title_msg)
        bottomPaddingTop = getPxSize(R.dimen.ios_bottom_padding_top)
        bottomPaddingBottom = getPxSize(R.dimen.ios_bottom_padding_bottom)
        bottomPaddingSides  = getPxSize(R.dimen.ios_bottom_padding_sides)
        bottomPaddingTitleMsg  = getPxSize(R.dimen.ios_bottom_padding_title_msg)
        return Config.build()
    }



    class Config {
        companion object {
            @JvmStatic
            fun build() = Config()
        }

        fun setCornerRadius(cornerRadius: Float) = this.apply {
            IDialog.cornerRadius = cornerRadius
        }

        fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) = this.apply { IDialog.alpha = alpha }

        fun setBackgroudColor(@ColorInt color: Int) = this.apply { bgColor = color }

        fun setAlertWidthScale(widthScale: Float) = this.apply { alertWidthScale = widthScale }

        fun setAlertPaddingTop(top: Int) = this.apply { alertPaddingTop = top }

        fun setAlertPadddingBottom(bottom: Int) = this.apply { alertPaddingBottom = bottom }

        fun setAlertPaddingSides(sides: Int) = this.apply { alertPaddingSides = sides }

        fun setAlertPaddingTitleMsg(padding: Int) = this.apply { alertPaddingTitleMsg = padding }

        @JvmOverloads
        fun setAlertTitle(textSize: Float = alertTitleConfigs.textSize,
                          @ColorInt textColor: Int = alertTitleConfigs.textColor,
                          text: String = "",
                          gravity: Int = alertTitleConfigs.gravity
        ) = this.apply {
            alertTitleConfigs.also {
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
            alertMsgConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        fun setAlertButtonHeight(height: Int) = this.apply { alertBtnHeight = height }

        fun setAlertAnimStyle(@StyleRes style: Int) = this.apply { alertAnimStyle = style }

        @JvmOverloads
        fun setAlertPosButton(text: String = alertPosBtnConfigs.text,
                              textSize: Float = alertPosBtnConfigs.textSize,
                              @ColorInt textColor: Int = alertPosBtnConfigs.textColor,
                              gravity: Int = alertPosBtnConfigs.gravity
        ) = this.apply {
            alertPosBtnConfigs.also {
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
            alertNegBtnConfigs.also {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
            }
        }

        @JvmOverloads
        fun setAlertListItem(textSize: Float = alertListItemConfigs.textSize,
                             @ColorInt textColor: Int = alertListItemConfigs.textColor,
                             height: Int = alertListItemConfigs.height,
                             paddingSides: Int = 0 //左右的padding值
        ) = this.apply {
            alertListItemConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
                it.paddingLeft = paddingSides
                it.paddingRight = paddingSides
            }
        }

        @JvmOverloads
        fun setBottomListTitle(textSize: Float = bottomTitleConfigs.textSize,
                               @ColorInt textColor: Int = bottomTitleConfigs.textColor,
                               text: String = "",
                               paddingLeft: Int = bottomTitleConfigs.paddingLeft,
                               paddingTop: Int = bottomTitleConfigs.paddingTop,
                               paddingRight: Int = bottomTitleConfigs.paddingRight,
                               paddingBottom: Int = bottomTitleConfigs.paddingBottom,
                               gravity: Int = bottomTitleConfigs.gravity
        ) = this.apply {
            bottomTitleConfigs.also {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.gravity = gravity
                it.paddingLeft = paddingLeft
                it.paddingTop = paddingTop
                it.paddingRight = paddingRight
                it.paddingBottom = paddingBottom
            }
        }

        @JvmOverloads
        fun setBottomListMsg(textSize: Float = bottomMsgConfigs.textSize,
                             @ColorInt textColor: Int = bottomMsgConfigs.textColor,
                             gravity: Int = bottomMsgConfigs.gravity
        ) = this.apply {
            bottomMsgConfigs.also {
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
            bottomBtnConfigs.also {
                it.text = text
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
            }
        }

        fun setBottomListItem(textSize: Float = bottomListItemConfigs.textSize,
                              @ColorInt textColor: Int = bottomListItemConfigs.textColor,
                              height: Int = bottomListItemConfigs.height,
                              paddingSides: Int = 0 //左右的padding值
        ) = this.apply {
            bottomListItemConfigs.also {
                it.textSize = textSize
                it.textColor = textColor
                it.height = height
                it.paddingLeft = paddingSides
                it.paddingRight = paddingSides
            }
        }

        fun setBottomPaddingTop(top: Int) = this.apply { bottomPaddingTop = top }

        fun setBottomPaddingBottom(bottom: Int) = this.apply { bottomPaddingBottom = bottom }

        fun setBottomPaddingSides(sides:Int) = this.apply { bottomPaddingSides = sides }

        fun setBottomPaddingTitleMsg(padding:Int) = this.apply { bottomPaddingTitleMsg = padding }

        /**
         * 底部对话框的动画样式
         */
        fun setBottomAnimStyle(@StyleRes style: Int) = this.apply { bottomAnimStyle = style }

        fun setBottomWidthScale(widthScale: Float) = this.apply { bottomWidthScale = widthScale }

    }
}