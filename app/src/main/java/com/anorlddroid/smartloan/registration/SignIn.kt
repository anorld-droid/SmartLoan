package com.anorlddroid.smartloan.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anorlddroid.smartloan.databinding.ActivitySignInBinding
import com.anorlddroid.smartloan.registration.signup.SignUp

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivitySignInBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.registerHere.setOnClickListener {
                val i  = Intent(this@SignIn, SignUp::class.java)
                startActivity(i)
                finish()
            }

    }


}