package com.anorlddroid.smartloan.registration.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.anorlddroid.smartloan.R
import com.anorlddroid.smartloan.databinding.SignUpFragmentBinding
import com.anorlddroid.smartloan.databinding.SignUpPaymentFragmentBinding
import kotlin.system.exitProcess

class SignUpPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpPaymentFragment()
    }

    private lateinit var viewModel: SignUpPaymentViewModel
    private lateinit var binding: SignUpPaymentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignUpPaymentFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SignUpPaymentViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.exit.observe(viewLifecycleOwner,
            Observer {
            if (it == true){
                exitProcess(0)
            }
        })
        return binding.root
    }



}