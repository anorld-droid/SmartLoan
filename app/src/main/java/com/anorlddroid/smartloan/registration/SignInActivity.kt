package com.anorlddroid.smartloan.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.anorlddroid.smartloan.MainActivity
import com.anorlddroid.smartloan.databinding.ActivitySignInBinding
import com.anorlddroid.smartloan.onboarding.COMPLETED_ONBOARDING_PREF_NAME
import com.anorlddroid.smartloan.onboarding.OnBoardingActivity
import com.anorlddroid.smartloan.registration.signup.SignUpActivity
import com.anorlddroid.smartloan.repository.PersonRepository

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private  val  personRepository: PersonRepository
    private val context : Context

    init {
        this.context = application
        personRepository = PersonRepository(context)
    }
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
                val i  = Intent(this@SignInActivity, MpesaNumberActivity::class.java)
                startActivity(i)
                finish()
            }

            binding.logInButton.setOnClickListener {
                if (loginValidation()) {
                    val i = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }

    }

    private fun loginValidation() : Boolean {
        val allInfo = personRepository.getAllInfo()
        for (pass in allInfo) {
            if (binding.phoneNumber.text.toString().toInt() != pass.phoneNumber) {
                binding.phoneNumber.error = "Invalid phone number"
                return false
            } else if (binding.password.text.toString().lowercase() != pass.password) {
                binding.password.error = "Invalid password"
                return false
            }
        }
        return true

        }
}