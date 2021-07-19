package com.anorlddroid.smartloan.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.anorlddroid.smartloan.MainActivity
import com.anorlddroid.smartloan.databinding.ActivitySignInBinding
import com.anorlddroid.smartloan.onboarding.COMPLETED_ONBOARDING_PREF_NAME
import com.anorlddroid.smartloan.onboarding.OnBoardingActivity
import com.anorlddroid.smartloan.registration.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivitySignInBinding.inflate(layoutInflater)
            setContentView(binding.root)
            PreferenceManager.getDefaultSharedPreferences(this).apply {
                if (!getBoolean(COMPLETED_ONBOARDING_PREF_NAME, false)) {
                    val i = Intent(this@SignInActivity, OnBoardingActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }

            binding.registerHere.setOnClickListener {
                val i  = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(i)
                finish()
            }

            binding.logInButton.setOnClickListener {
                val i  = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }
    }


}