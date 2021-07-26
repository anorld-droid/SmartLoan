package com.anorlddroid.smartloan.registration.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.anorlddroid.smartloan.R
import com.anorlddroid.smartloan.database.UserEntity
import com.anorlddroid.smartloan.databinding.SignUpFragmentBinding
import java.util.*


class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var binding: SignUpFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToSignUpPaymentFragment.observe(
            viewLifecycleOwner,
            Observer { shouldNavigate ->
                if (shouldNavigate == true) {
                    if (saveToDatabase()) {

                        val navController = activity?.let { it1 ->
                            Navigation.findNavController(it1, R.id.fragment_container_view)
                        }
                        navController?.navigate(R.id.action_signUpFragment_to_signUpPaymentFragment)
                        viewModel.onNavigatedToSignUpPaymentFragment()
                    }
                }

            })
        return binding.root
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
            binding.dateOfBirth.text.toString().isEmpty() -> {
                binding.dateOfBirth.error = "Date of birth cannot be null"
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
        userEntity.dateOfBirth = binding.dateOfBirth.text.toString().toInt()
        userEntity.password = binding.password.text.toString()
        return run {
            Thread {
                viewModel.registerUser(userEntity)
            }.start()
            val name = binding.firstName.text.toString()
            Toast.makeText(
                this.context, "$name, your  account has been successfully created",
                Toast.LENGTH_LONG
            ).show()
            Log.d(tag, "Inserted successfully")
            true
        }

    }
}