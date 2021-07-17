package com.anorlddroid.smartloan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.anorlddroid.smartloan.onboarding.COMPLETED_ONBOARDING_PREF_NAME
import com.anorlddroid.smartloan.onboarding.OnBoardingActivity
import com.anorlddroid.smartloan.registration.SignIn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            if (!getBoolean(COMPLETED_ONBOARDING_PREF_NAME, false)) {
                val i = Intent(this@MainActivity, OnBoardingActivity::class.java)
                startActivity(i)
                finish()
            }else {
                val i = Intent(this@MainActivity, SignIn::class.java)
                startActivity(i)
                finish()
            }
        }
    }
} 