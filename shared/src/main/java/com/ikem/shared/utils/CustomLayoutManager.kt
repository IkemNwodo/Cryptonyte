package com.ikem.nwodo.cryptonyte.utils

import android.content.Context
import android.util.DisplayMetrics
import android.graphics.PointF
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class CustomLayoutManager(private val mContext: Context?) : LinearLayoutManager(mContext, HORIZONTAL, false) {

    override fun smoothScrollToPosition(recyclerView: RecyclerView,
                                        state: RecyclerView.State?, position: Int) {

        val smoothScroller = object : LinearSmoothScroller(mContext) {

            //This controls the direction in which smoothScroll looks
            //for your view
            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                return this@CustomLayoutManager
                        .computeScrollVectorForPosition(targetPosition)
            }

            //This returns the milliseconds it takes to
            //scroll one pixel.
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
            }
        }

        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    companion object {
        private val MILLISECONDS_PER_INCH = 50f
    }
}