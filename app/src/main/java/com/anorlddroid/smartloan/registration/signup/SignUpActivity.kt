package com.anorlddroid.smartloan.registration.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.anorlddroid.smartloan.registration.RegistrationPaymentActivity
import com.anorlddroid.smartloan.database.UserEntity
import com.anorlddroid.smartloan.databinding.ActivitySignUpBinding
import timber.log.Timber

open class SignUpActivity : AppCompatActivity() {

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
                val title = "Congratulations!"
                val message = "Pay ksh.250 to get your loan"
               dialogBox(title, message)
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
        userEntity.nationalID = binding.nationalId.text.toString().toLongOrNull()
        userEntity.password = binding.password.text.toString()
        userEntity.paymentStatus = 0
        return run {
            Thread {
                viewModel.registerUser(userEntity)
            }.start()

            Timber.d("Inserted successfully")
            true
        }

    }
    private fun dialogBox(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Continue"){ _, _ ->
            val i  = Intent(this, RegistrationPaymentActivity::class.java)
            startActivity(i)
            finish()
        }
        val alertDialog : AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}