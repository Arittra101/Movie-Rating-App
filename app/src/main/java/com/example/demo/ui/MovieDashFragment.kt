package com.example.demo.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.demo.R
import com.example.demo.databinding.FragmentMovieDasBinding
import com.example.demo.ui.slider.SliderAdapter
import kotlin.math.abs

class MovieDashFragment : Fragment(R.layout.fragment_movie_das) {

    private lateinit var binding: FragmentMovieDasBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: SliderAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDasBinding.bind(view)

        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = (viewPager2.currentItem + 1) % adapter.itemCount
    }

//    private fun setUpTransformer() {
//        val transformer = CompositePageTransformer()
//        transformer.addTransformer(MarginPageTransformer(40)) // Adjust margin for spacing
//
//        transformer.addTransformer { page, position ->
//            val absPosition = abs(position)
//
//            // Apply scaling and alpha for smooth transitions
//            val scaleFactor = 0.85f + (1 - absPosition) * 0.15f
//            val alphaFactor = 0.5f + (1 - absPosition) * 0.5f
//
//            page.scaleY = scaleFactor
//            page.scaleX = scaleFactor
//            page.alpha = alphaFactor
//
//            // Apply rotation based on position
//            val rotationAngle = 15f // Adjust this value for the desired rotation angle
//            when {
//                position < 0 -> {
//                    // 1st image (left of the center)
//                    page.rotationY = -rotationAngle * absPosition // Rotate left
//                }
//                position > 0 -> {
//                    // 3rd image (right of the center)
//                    page.rotationY = rotationAngle * absPosition // Rotate right
//                }
//                else -> {
//                    // 2nd image (center)
//                    page.rotationY = 0f // No rotation for the center image
//                }
//            }
//        }
//
//        viewPager2.setPageTransformer(transformer)
//    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(80))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }


    private fun init() {
        viewPager2 = binding.viewPager
        handler = Handler(Looper.getMainLooper())
        imageList = arrayListOf(
            R.drawable.ediots,
            R.drawable.ediots,
            R.drawable.ediots,
            R.drawable.ediots,
            R.drawable.ediots,
            R.drawable.ediots,
            R.drawable.ediots,
            R.drawable.ediots
        )
        adapter = SliderAdapter(imageList,viewPager2)
        viewPager2.adapter = adapter
        viewPager2.setCurrentItem(1, false)
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}
