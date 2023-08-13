package com.example.viewpager2

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var imagePagerAdapter:ImagePagerAdapter

    private val pageChangeCallback= object: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setCurrentIndicator(position)
        }
    }
    override fun initView(savedInstanceState: Bundle?) {
        setImagePagerAdapter()
        setViewPager2Adapter()
        setupIndicator()
        setCurrentIndicator(0)
    }

    private fun setImagePagerAdapter(){
        imageList(this)
        imagePagerAdapter= ImagePagerAdapter(imageList(this))
    }

    fun navigateToNextPage(viewPager2: ViewPager2){
        val nextPage:Int= viewPager2.currentItem.plus(1)
        viewPager2.setCurrentItem(nextPage,true)
    }

    private fun setViewPager2Adapter(){
        with(binding){
            viewPager2.adapter=imagePagerAdapter
            viewPager2.registerOnPageChangeCallback(pageChangeCallback)
        }
    }

    private fun setCurrentIndicator(position:Int){
        with(binding){
            val childCount = linearLayout2.childCount

            for (i in 0 until childCount) {
                val imageView = linearLayout2[i] as ImageView
                var imageDrawable = R.drawable.ic_dot

                if (i == position) {
                    imageDrawable = R.drawable.ic_dot_selected
                }

                imageView.setImageDrawable(
                    ContextCompat.getDrawable(this@MainActivity, imageDrawable)
                )
            }
        }
    }

    private fun setupIndicator(){
        with(binding){
            val dotIndicators= arrayOfNulls<ImageView>(imagePagerAdapter.itemCount)
            val layoutParams= LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
            layoutParams.setMargins(8,0,8,0)
            dotIndicators.indices.forEach { i->
                dotIndicators[i] = ImageView(this@MainActivity)
                dotIndicators[i]?.apply {
                    this.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@MainActivity,
                            R.drawable.ic_dot_selected
                        )
                    )
                    this.layoutParams = layoutParams
                    linearLayout2.addView(dotIndicators[i])
                }
            }
        }
    }


    private fun imageList(context: Context): ArrayList<ImageItem> {
        return arrayListOf(
            ImageItem(R.drawable.ic_launcher_background, desc = context.getString(R.string.item1)),
            ImageItem(R.drawable.ic_launcher_background, desc = context.getString(R.string.item1)),
            ImageItem(R.drawable.ic_launcher_background, desc = context.getString(R.string.item1))
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager2.unregisterOnPageChangeCallback(pageChangeCallback)
    }

}