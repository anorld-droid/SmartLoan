package com.anorlddroid.smartloan.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.anorlddroid.smartloan.MainActivity
import com.anorlddroid.smartloan.database.UserDatabase
import com.anorlddroid.smartloan.database.UserEntity
import com.anorlddroid.smartloan.databinding.ActivitySignInBinding
import com.anorlddroid.smartloan.onboarding.COMPLETED_ONBOARDING_PREF_NAME
import com.anorlddroid.smartloan.onboarding.OnBoardingActivity
import com.anorlddroid.smartloan.registration.signup.SignUpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private lateinit var viewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            if (!getBoolean(COMPLETED_ONBOARDING_PREF_NAME, false)) {
                val i = Intent(this@SignInActivity, OnBoardingActivity::class.java)
                startActivity(i)
                finish()
            }
        }


        binding.registerHere.setOnClickListener {
            val i = Intent(this@SignInActivity, MpesaNumberActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.logInButton.setOnClickListener {
            if (loginValidation()) {
                if (validatePayment()) {
                    Toast.makeText(
                        this, "Pay your registration fee then login.",
                        Toast.LENGTH_LONG
                    ).show()
                    val i = Intent(this@SignInActivity, RegistrationPaymentActivity::class.java)
                    startActivity(i)
                    finish()
                }else{
                    Toast.makeText(
                        this, "Log in successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val i = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }

            }

        }
    }

    private fun validatePayment(): Boolean {
        val userDatabase: UserDatabase? = UserDatabase.getUserDatabase(applicationContext)
        val paymentStatus = userDatabase?.userDao()?.getPaymentStatus()
        return if (paymentStatus == null) {
            true
        }else {
            true
        }
}

private fun loginValidation(): Boolean {
    val userDatabase: UserDatabase? = UserDatabase.getUserDatabase(applicationContext)
    val allInfo = userDatabase?.userDao()?.getLogInInfo()

    when {
        binding.phoneNumber.text.toString().isEmpty() -> {
            binding.phoneNumber.error = "Phone number cannot be empty"
            return false
        }
        binding.password.text.toString().isEmpty() -> {
            binding.password.error = "Password cannot be empty"
            return false
        }
        binding.phoneNumber.text.toString().toIntOrNull() == null -> {
            binding.phoneNumber.error = "Invalid Phone Number"
            return false
        }
        allInfo != null -> {
            for (pass in allInfo) {
                when {
                    pass.phoneNumber != null -> {
                        if (binding.phoneNumber.text.toString() != pass.phoneNumber) {
                            binding.phoneNumber.error = "Invalid phone number"
                            return false
                        }
                    }
                    pass.password != null -> {
                        return if (binding.password.text.toString()
                                .lowercase() != pass.password?.lowercase()
                        ) {
                            binding.password.error = "Invalid password"
                            false
                        } else {

                            true
                        }
                    }
                }
            }
        }
    }
    Toast.makeText(
        this, "Create an account then try again",
        Toast.LENGTH_LONG
    ).show()
    return false
}

}