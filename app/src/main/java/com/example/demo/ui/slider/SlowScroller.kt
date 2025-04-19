package com.example.demo.ui.slider
import android.content.Context
import android.view.animation.Interpolator
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class SlowScroller(context: Context) : LinearSmoothScroller(context) {
    fun getInterpolator(): Interpolator {
        return android.view.animation.DecelerateInterpolator() // Smooth deceleration
    }

    override fun calculateSpeedPerPixel(displayMetrics: android.util.DisplayMetrics): Float {
        return 0.2f // Slow scrolling speed (increase the number for slower speed)
    }
}

