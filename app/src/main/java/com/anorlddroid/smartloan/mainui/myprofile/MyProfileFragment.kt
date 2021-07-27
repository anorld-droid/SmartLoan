package com.anorlddroid.smartloan.mainui.myprofile

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anorlddroid.smartloan.database.UserEntity
import com.anorlddroid.smartloan.databinding.FragmentMyProfileBinding


class MyProfileFragment : Fragment() {

    private lateinit var viewModel: MyProfileViewModel
    private lateinit var binding: FragmentMyProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(MyProfileViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.infoUpdated.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                viewModel.getAllInfo()?.forEach { user ->
                    if (user.firstName != null ) {
                        binding.firstNameEditText.text = Editable.Factory.getInstance().newEditable(user.firstName)
                        binding.lastNameEditText.text = SpannableStringBuilder(user.lastName)
                        binding.emailEditText.text = SpannableStringBuilder(user.email)
                        binding.nationalIdEditText.text = SpannableStringBuilder(user.nationalID.toString())
                        binding.dateOfBirthEditText.text = SpannableStringBuilder(user.dateOfBirth.toString())
                        binding.passwordEditText.text = SpannableStringBuilder(user.password)
                        viewModel.uiUpdated()
                    }
                }
            }
        })

        viewModel.saveChanges.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                if (saveToDatabase()) {
                    viewModel.onInfoUpdated()
                }
            }
        })

        return binding.root

    }

    private fun saveToDatabase(): Boolean {
        when {
            binding.firstNameEditText.text.toString().isEmpty() -> {
                binding.firstNameEditText.error = "First Name cannot be null"
                return false
            }
            binding.lastNameEditText.text.toString().isEmpty() -> {
                binding.lastNameEditText.error = "Last Name cannot be null"
                return false

            }
            binding.emailEditText.text.toString().isEmpty() -> {
                binding.emailEditText.error = "Email cannot be null"
                return false

            }
            binding.nationalIdEditText.text.toString().isEmpty() -> {
                binding.nationalIdEditText.error = "National ID cannot be null"
                return false

            }
            binding.dateOfBirthEditText.text.toString().isEmpty() -> {
                binding.dateOfBirthEditText.error = "Date of birth cannot be null"
                return false

            }
            binding.passwordEditText.text.toString().isEmpty() -> {
                binding.passwordEditText.error = "Password cannot be null"
                return false

            }
            binding.passwordEditText.text.toString().length < 6 -> {
                binding.passwordEditText.error = "Password should have atleast 6 characters"
                return false

            }

        }
        val userEntity = UserEntity()
        userEntity.firstName = binding.firstNameEditText.text.toString()
        userEntity.lastName = binding.lastNameEditText.text.toString()
        userEntity.email = binding.emailEditText.text.toString()
        userEntity.nationalID = binding.nationalIdEditText.text.toString().toIntOrNull()
        userEntity.dateOfBirth = binding.dateOfBirthEditText.text.toString().toIntOrNull()
        userEntity.password = binding.passwordEditText.text.toString()
        return run {
            Thread {
                viewModel.deleteUser(userEntity)
                viewModel.insertUser(userEntity)
            }.start()
            Toast.makeText(
                this.context, "Changes saved",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

    }

}