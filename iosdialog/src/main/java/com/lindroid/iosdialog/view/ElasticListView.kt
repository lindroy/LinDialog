package com.lindroid.iosdialog.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView
import com.lindroid.iosdialog.util.dp2px

/**
 * @author Lin
 * @date 2019/5/19
 * @function
 * @description
 */
class ElasticListView : ListView {
    private var maxOverDistance = dp2px(100F).toInt()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, android.R.attr.listViewStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        val metrics = context.resources.displayMetrics
        val density: Float = metrics.density
        maxOverDistance = (density * maxOverDistance).toInt()
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