package com.example.demo.ui.slider

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class DepthPageTransformer : ViewPager2.PageTransformer {
    private val nextItemVisiblePx = 50f
    private val currentItemHorizontalMarginPx = 100f
    private val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

    override fun transformPage(page: View, position: Float) {
        page.apply {
            when {
                position <= -1 -> { // [-Infinity,-1]
                    // This page is way off-screen to the left
                    alpha = 0.3f
                    translationX = -pageTranslationX
                    scaleY = 0.85f
                }
                position <= 1 -> { // (-1,1)
                    // Modify the default slide transition
                    val scaleFactor = 0.85f.coerceAtLeast(1 - kotlin.math.abs(position))
                    val vertMargin = height * (1 - scaleFactor) / 2
                    val horMargin = width * (1 - scaleFactor) / 2

                    // Center vertically
                    translationY = if (position < 0) {
                        vertMargin - horMargin / 2
                    } else {
                        -vertMargin + horMargin / 2
                    }

                    // Scale the page down
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size
                    alpha = (0.3f + (((scaleFactor - 0.85f) / (1 - 0.85f)) * (1 - 0.3f)))

                    // Translation for proper stacking
                    translationX = position * -pageTranslationX
                }
                else -> { // [1,+Infinity]
                    // This page is way off-screen to the right
                    alpha = 0.3f
                    translationX = pageTranslationX
                    scaleY = 0.85f
                }
            }
        }
    }
}
