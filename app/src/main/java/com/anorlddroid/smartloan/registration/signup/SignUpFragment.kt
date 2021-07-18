package com.anorlddroid.smartloan.registration.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.anorlddroid.smartloan.R
import com.anorlddroid.smartloan.databinding.SignUpFragmentBinding


class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

//    private lateinit var binding : SignupFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SignUpFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.viewModel = viewModel

        binding.createAccount.setOnClickListener {

            val navController = activity?.let { it1 ->
                Navigation.findNavController(it1, R.id.fragment_container_view) }
            navController?.navigate(R.id.action_signUpFragment_to_signUpPaymentFragment)
            viewModel.onNavigatedToSignUpPaymentFragment()
        }
        return binding.root


    }


    fun loadFragment(fragment : Fragment){
        val fragmentTransaction : FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_container_view, fragment )
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }


}