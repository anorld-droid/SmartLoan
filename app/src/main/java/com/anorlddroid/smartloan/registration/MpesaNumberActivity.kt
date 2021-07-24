package com.anorlddroid.smartloan.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import com.anorlddroid.smartloan.database.PersonEntity
import com.anorlddroid.smartloan.databinding.ActivityMpesaNumberBinding
import com.anorlddroid.smartloan.registration.signup.SignUpActivity
import com.anorlddroid.smartloan.repository.PersonRepository

class MpesaNumberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMpesaNumberBinding
    private  val  personRepository: PersonRepository = PersonRepository(applicationContext)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMpesaNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.continueButton.setOnClickListener {
            if (validateNumber()){

                val i  = Intent(this, SignUpActivity::class.java)
                startActivity(i)
                finish()
            }

        }

    }
    fun validateNumber() : Boolean{
        if (binding.editTextPhone.text.toString().isEmpty()){
            binding.editTextPhone.error = "Phone Number cannot be null"
            return false
        }else if (binding.editTextPhone.text.toString().length < 13){
            binding.editTextPhone.error = "Should be of atleast 13 characters"
            return false
        }else {
            val personEntity : PersonEntity? = null
            val phoneNumber  = binding.editTextPhone.text.toString().toInt()
            personEntity?.phoneNumber = phoneNumber
            if (personEntity != null) {
                personRepository.insertPhoneNumber(personEntity)
            }
            return true
        }

    }
}