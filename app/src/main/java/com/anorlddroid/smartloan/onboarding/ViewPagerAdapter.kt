package com.anorlddroid.smartloan.onboarding

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import android.widget.LinearLayout
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.anorlddroid.smartloan.R

class ViewPagerAdapter(var context: Context) : PagerAdapter() {
    var images = intArrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    )
    var headings = intArrayOf(
        R.string.heading_one,
        R.string.heading_two,
        R.string.heading_three,
        R.string.heading_fourth
    )
    var description = intArrayOf(
        R.string.desc_one,
        R.string.desc_two,
        R.string.desc_three,
        R.string.desc_fourth
    )

    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slider_layout, container, false)
        val slidetitleimage = view.findViewById<View>(R.id.titleImage) as ImageView
        val slideHeading = view.findViewById<View>(R.id.texttitle) as TextView
        val slideDesciption = view.findViewById<View>(R.id.textdeccription) as TextView
        slidetitleimage.setImageResource(images[position])
        slideHeading.setText(headings[position])
        slideDesciption.setText(description[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}