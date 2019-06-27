package com.lindroid.iosdialog.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

/**
 * @author Lin
 * @date 2019/5/19
 * @function 具有回弹效果的ListView
 * @description
 */
private const val MAX_Y_OVERSCROLL_DISTANCE = 100
class ElasticListView : ListView {
    private var maxOverDistance = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.listViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        val metrics = context.resources.displayMetrics
        val density: Float = metrics.density
        maxOverDistance = (density * MAX_Y_OVERSCROLL_DISTANCE).toInt()
    }

    override fun overScrollBy(
        deltaX: Int,
        deltaY: Int,
        scrollX: Int,
        scrollY: Int,
        scrollRangeX: Int,
        scrollRangeY: Int,
        maxOverScrollX: Int,
        maxOverScrollY: Int,
        isTouchEvent: Boolean
    ): Boolean {
        return super.overScrollBy(
            deltaX,
            deltaY,
            scrollX,
            scrollY,
            scrollRangeX,
            scrollRangeY,
            maxOverScrollX,
            maxOverDistance,
            isTouchEvent
        )
    }
}