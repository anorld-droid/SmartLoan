package com.anorlddroid.smartloan.registration.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.anorlddroid.smartloan.registration.RegistrationPaymentActivity
import com.anorlddroid.smartloan.database.UserEntity
import com.anorlddroid.smartloan.databinding.ActivitySignUpBinding
import timber.log.Timber

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.viewModel = viewModel

        binding.createAccount.setOnClickListener {
            if (saveToDatabase()) {
                val i  = Intent(this, RegistrationPaymentActivity::class.java)
                startActivity(i)
                finish()
            }
        }


    }


    private fun saveToDatabase(): Boolean {
        when {
            binding.firstName.text.toString().isEmpty() -> {
                binding.firstName.error = "First Name cannot be null"
                return false
            }
            binding.lastName.text.toString().isEmpty() -> {
                binding.lastName.error = "Last Name cannot be null"
                return false

            }
            binding.email.text.toString().isEmpty() -> {
                binding.email.error = "Email cannot be null"
                return false

            }
            binding.nationalId.text.toString().isEmpty() -> {
                binding.nationalId.error = "National ID cannot be null"
                return false
            }
            binding.password.text.toString().isEmpty() -> {
                binding.password.error = "Password cannot be null"
                return false

            }
            binding.password.text.toString().length < 6 -> {
                binding.password.error = "Password should have atleast 6 characters"
                return false

            }
            binding.confirmPassword.text.toString().isEmpty() -> {
                binding.confirmPassword.error = "Field cannot be null"
                return false

            }
            binding.confirmPassword.text.toString().lowercase()
                    != binding.password.text.toString().lowercase() -> {
                binding.confirmPassword.error = "Password does not match"
                return false

            }
        }
        val userEntity = UserEntity()
        userEntity.firstName = binding.firstName.text.toString()
        userEntity.lastName = binding.lastName.text.toString()
        userEntity.email = binding.email.text.toString()
        userEntity.nationalID = binding.nationalId.text.toString().toInt()
        userEntity.password = binding.password.text.toString()
        return run {
            Thread {
                viewModel.registerUser(userEntity)
            }.start()
            val name = binding.firstName.text.toString()
            Toast.makeText(
                this, "$name, your  account has been successfully created",
                Toast.LENGTH_LONG
            ).show()
            Timber.d("Inserted successfully")
            true
        }

    }

}