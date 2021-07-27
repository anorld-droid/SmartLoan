package com.anorlddroid.smartloan.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.anorlddroid.smartloan.database.UserDatabase
import com.anorlddroid.smartloan.database.UserEntity
import com.anorlddroid.smartloan.databinding.ActivityMpesaNumberBinding
import com.anorlddroid.smartloan.registration.signup.SignUpActivity
import com.anorlddroid.smartloan.registration.signup.SignUpViewModel

class MpesaNumberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMpesaNumberBinding
    private lateinit var viewModel: SignUpViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMpesaNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.continueButton.setOnClickListener {
            if (validateNumber()){

                val i  = Intent(this, SignUpActivity::class.java)
                startActivity(i)
                finish()
            }

        }

    }
    private fun validateNumber() : Boolean{
        when {
            binding.editTextPhone.text.toString().isEmpty() -> {
                binding.editTextPhone.error = "Phone Number cannot be empty"
                return false
            }
            binding.editTextPhone.text.toString().length < 10 -> {
                binding.editTextPhone.error = "Should be of at least 10 characters"
                return false
            }
            binding.editTextPhone.text.toString().toIntOrNull() == null -> {
                binding.editTextPhone.error = "Invalid Phone Number"
                return false
            }
            else -> {
                val userEntity = UserEntity()
                val phoneNumber  = binding.editTextPhone.text.toString()
                userEntity.phoneNumber = phoneNumber
                val userDatabase : UserDatabase? = UserDatabase.getUserDatabase(applicationContext)
                Thread {
                    userDatabase?.userDao()?.registerPhoneNumber(userEntity)
                }.start()
                Toast.makeText(
                    this, "Your  phone number has been successfully created",
                    Toast.LENGTH_LONG
                ).show()
                return true
            }
        }

    }
}