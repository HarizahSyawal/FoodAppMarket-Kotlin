package com.hariz.foodmarketapp.ui.auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.hariz.foodmarketapp.R
import com.hariz.foodmarketapp.databinding.FragmentSignupAddressBinding
import com.hariz.foodmarketapp.databinding.FragmentSignupBinding
import com.hariz.foodmarketapp.ui.auth.AuthActivity

class SignupAddressFragment : Fragment() {
    private lateinit var binding: FragmentSignupAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_signup_address, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup_address, container, false)
        // Set the click listener using data binding
        binding.btnSignUpNow.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_signup_success, null)
            (activity as AuthActivity).toolbarSignUpAddress()
        }

        return binding.root
    }
}
