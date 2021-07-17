package com.anorlddroid.SmartLoan

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.anorlddroid.SmartLoan.onboarding.COMPLETED_ONBOARDING_PREF_NAME
import com.anorlddroid.SmartLoan.onboarding.OnBoardingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val sharedPreferences : SharedPreferences
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
//        if (!sharedPreferences.getBoolean(COMPLETED_ONBOARDING_PREF_NAME, false)) {
//            startActivity(Intent(this, OnBoardingActivity::class.java))
//        }
    }
}