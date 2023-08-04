package com.hariz.foodmarketapp.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.hariz.foodmarketapp.R
import com.hariz.foodmarketapp.databinding.FragmentSigninBinding
import com.hariz.foodmarketapp.databinding.FragmentSignupBinding
import com.hariz.foodmarketapp.ui.auth.AuthActivity

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        // Set the click listener using data binding
        binding.btnContinue.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.fragmentSignUpAdress, null)
        }

        return binding.root
    }
}
