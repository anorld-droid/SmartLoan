package com.anorlddroid.smartloan.registration.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.anorlddroid.smartloan.R

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
         val fragment : Fragment
         fragment = SignUpFragment()
         loadFragment(fragment)


    }

    fun loadFragment(fragment : Fragment){
        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_view, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}