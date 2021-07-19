package com.anorlddroid.smartloan.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anorlddroid.smartloan.databinding.ActivityMpesaNumberBinding
import com.anorlddroid.smartloan.registration.signup.SignUpActivity

class MpesaNumberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMpesaNumberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMpesaNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.continueButton.setOnClickListener {
            val i  = Intent(this, SignUpActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}