package com.anorlddroid.smartloan.onboarding

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.anorlddroid.smartloan.MainActivity
import com.anorlddroid.smartloan.R
import com.anorlddroid.smartloan.registration.SignIn

const val COMPLETED_ONBOARDING_PREF_NAME : String = "Onboarding Completed"
open class OnBoardingActivity : AppCompatActivity() {
    var mSLideViewPager: ViewPager? = null
    var mDotLayout: LinearLayout? = null
    var backbtn: Button? = null
    var nextbtn: Button? = null
    var skipbtn: Button? = null
    lateinit var dots: Array<TextView?>
    var viewPagerAdapter: ViewPagerAdapter? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boarding)
        backbtn = findViewById(R.id.backbtn)
        nextbtn = findViewById(R.id.nextbtn)
        skipbtn = findViewById(R.id.skipButton)
        backbtn?.setOnClickListener {
            if (getitem(0)!! > 0) {
                getitem(-1)?.let { it1 -> mSLideViewPager!!.setCurrentItem(it1, true) }
            }
        }
        nextbtn?.setOnClickListener {
            if (getitem(0)!! < 3) getitem(1)?.let {
                    it1 -> mSLideViewPager!!.setCurrentItem(it1, true)
            }
            else {
                val i  = Intent(this@OnBoardingActivity, SignIn::class.java)
                startActivity(i)
                finish()
            }
        }
        skipbtn?.setOnClickListener {
            val i = Intent(this@OnBoardingActivity, SignIn::class.java)
            startActivity(i)
            finish()
        }
        mSLideViewPager = findViewById(R.id.slideViewPager) as? ViewPager
        mDotLayout = findViewById<View>(R.id.indicator_layout) as? LinearLayout
        viewPagerAdapter = ViewPagerAdapter(this)
        mSLideViewPager?.adapter = viewPagerAdapter
        setUpindicator(0)
        mSLideViewPager?.addOnPageChangeListener(viewListener)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setUpindicator(position: Int) {
        dots = arrayOfNulls(4)
        mDotLayout?.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("&#8226")
            dots[i]?.textSize = 35f
            dots[i]?.setTextColor(resources.getColor(R.color.inactive, applicationContext.theme))
            mDotLayout?.addView(dots[i])
        }
        dots[position]?.setTextColor(resources.getColor(R.color.active, applicationContext.theme))
    }

    var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onPageSelected(position: Int) {
            setUpindicator(position)
            if (position > 0) {
                backbtn?.visibility = View.VISIBLE
            } else {
                backbtn?.visibility = View.INVISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    override fun finish() {
        super.finish()
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply {
            putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
            apply()
        }
    }
    private fun getitem(i: Int): Int? {
        return mSLideViewPager?.currentItem?.plus(i)
    }
}
