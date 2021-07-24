package com.anorlddroid.smartloan.registration.signup

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.anorlddroid.smartloan.R
import com.anorlddroid.smartloan.database.PersonEntity
import com.anorlddroid.smartloan.databinding.SignUpFragmentBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates


class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var binding : SignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  SignUpFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToSignUpPaymentFragment.observe(viewLifecycleOwner, Observer { shouldNavigate ->
            if (shouldNavigate == true){
                if (saveToDatabase()) {
                    val name = binding.firstName.text.toString()
                    Toast.makeText(this.context, "$name, your  account has been successfully created",
                        Toast.LENGTH_LONG).show()
                    val navController = activity?.let { it1 ->
                        Navigation.findNavController(it1, R.id.fragment_container_view) }
                    navController?.navigate(R.id.action_signUpFragment_to_signUpPaymentFragment)
                    viewModel.onNavigatedToSignUpPaymentFragment()
                }
                }

        })
        return binding.root
     }


     private fun saveToDatabase(): Boolean {
         if (binding.firstName.text.toString().isEmpty()){
             binding.firstName.error = "First Name cannot be null"
             return false
         }
         else if (binding.lastName.text.toString().isEmpty()){
             binding.lastName.error = "Last Name cannot be null"
             return false

         }
         else if (binding.email.text.toString().isEmpty()){
             binding.email.error = "Email cannot be null"
             return false

         }
         else if (binding.nationalId.text.toString().isEmpty()){
             binding.nationalId.error = "National ID cannot be null"
             return false

         }
         else if (binding.dateOfBirth.text.toString().isEmpty()){
             binding.dateOfBirth.error = "Date of birth cannot be null"
             return false

         }
         else if (binding.password.text.toString().isEmpty()){
            binding.password.error = "Password cannot be null"
             return false

         }
         else if (binding.password.text.toString().length < 6){
             binding.password.error = "Password should have atleast 6 characters"
             return false

         }
         else if (binding.confirmPassword.text.toString().isEmpty()){
            binding.confirmPassword.error = "Field cannot be null"
             return false

         }
         else if (binding.confirmPassword.text.toString().lowercase()
             != binding.password.text.toString().lowercase()
         ){
            binding.confirmPassword.error = "Password does not match"
             return false

         }
         else{
             val personEntity : PersonEntity? = null
             personEntity?.firstName = binding.firstName.text.toString()
             personEntity?.lastName = binding.lastName.toString()
             personEntity?.email = binding.email.text.toString()
             personEntity?.nationalID = binding.nationalId.text.toString().toInt()
             val stringDate = binding.dateOfBirth.text.toString()
             personEntity?.dateOfBirth = Date.parse(stringDate)
             personEntity?.password = binding.password.text.toString()
            if (personEntity != null) {
                 viewModel.insertInfo(personEntity)
                 Log.d(tag, "Inserted successfully")
                 return true
             }
             return true
         }
     }
}