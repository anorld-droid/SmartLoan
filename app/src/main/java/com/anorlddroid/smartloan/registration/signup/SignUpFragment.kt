package com.anorlddroid.smartloan.registration.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.anorlddroid.smartloan.R
import com.anorlddroid.smartloan.databinding.SignUpFragmentBinding

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

//    private lateinit var binding : SignupFragmentBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SignUpFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateToSignUpPaymentFragment.observe(viewLifecycleOwner,
            Observer<Boolean> { shouldNavigate ->
                if (shouldNavigate == true) {
                    val fragmentTransaction : FragmentTransaction? =
                        activity?.supportFragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_container_view, SignUpPaymentFragment())
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.commit()
                    viewModel.onNavigatedToSignUpPaymentFragment()
                }
            })
        return inflater.inflate(R.layout.sign_up_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
    }

}