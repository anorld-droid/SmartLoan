package com.anorlddroid.smartloan

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.anorlddroid.smartloan.database.UserDatabase
import com.anorlddroid.smartloan.databinding.ActivityMainBinding
import com.anorlddroid.smartloan.mainui.about.AboutActivity
import com.anorlddroid.smartloan.onboarding.COMPLETED_ONBOARDING_PREF_NAME
import com.anorlddroid.smartloan.onboarding.OnBoardingActivity
import com.anorlddroid.smartloan.registration.MpesaNumberActivity
import com.anorlddroid.smartloan.registration.RegistrationPaymentActivity
import com.anorlddroid.smartloan.registration.SignInActivity
import com.anorlddroid.smartloan.registration.signup.SignUpViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_about
            )
        )
        val navView: BottomNavigationView = binding.navView

        val navController : NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Adds about to the action bar
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bar_about){
            val i  = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(i)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

} 