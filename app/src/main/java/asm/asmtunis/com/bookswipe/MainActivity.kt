package asm.asmtunis.com.bookswipe

import android.animation.ArgbEvaluator
import android.content.Context
import android.os.Bundle
import android.view.View.OnScrollChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import asm.asmtunis.com.bookswipe.fragment.FirstFragment
import asm.asmtunis.com.bookswipe.fragment.SecondFragment
import asm.asmtunis.com.bookswipe.fragment.ThirdFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val NUM_PAGES = 3
class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    lateinit var context: Context
    lateinit var evaluator: ArgbEvaluator
    lateinit var colors: IntArray
    private lateinit var bottom_navigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        evaluator= ArgbEvaluator()
        context=this
        colors= intArrayOf(context.getColor(R.color.yellow),
            context.getColor(R.color.orange),
            context.getColor(R.color.night))
        initBottomNavigationView()
        initPager()

    }

    private fun initBottomNavigationView() {
        bottom_navigation= findViewById(R.id.bottom_navigation)
        bottom_navigation.itemIconTintList = null
    }

    private fun initPager() {
        viewPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter


        viewPager.setOnClickListener {
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position < pagerAdapter.itemCount - 1 && position < colors.size - 1) {
                    viewPager.setBackgroundColor((evaluator.evaluate(positionOffset,colors[position],colors[position+1]) as Int))
                }else
                    viewPager.setBackgroundColor(colors[colors.size - 1])


            }
        })





    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment{
            return when(position) {
                0 -> {
                    FirstFragment()
                }
                1 -> {
                    SecondFragment()
                }
                else -> {
                    ThirdFragment()
                }
            }
        }



    }
}

private fun ViewPager2.setOnScrollChangeListener(
    onScrollChangeListener: OnScrollChangeListener,
    function: () -> Unit
) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
