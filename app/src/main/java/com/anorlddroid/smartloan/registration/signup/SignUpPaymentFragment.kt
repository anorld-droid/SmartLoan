package com.anorlddroid.smartloan.registration.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anorlddroid.smartloan.R

class SignUpPaymentFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpPaymentFragment()
    }

    private lateinit var viewModel: SignUpPaymentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_up_payment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpPaymentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}