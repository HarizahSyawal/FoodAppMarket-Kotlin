package com.hariz.foodmarketapp.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hariz.foodmarketapp.R
import com.hariz.foodmarketapp.databinding.FragmentSigninBinding
import com.hariz.foodmarketapp.ui.MainActivity
import com.hariz.foodmarketapp.ui.auth.AuthActivity


class SigninFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_signin, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signin, container, false)

        // Set the click listener using data binding
        binding.btnSignin.setOnClickListener {
            onButtonClickSigIn()
        }

        binding.btnSignup.setOnClickListener {
            onButtonClickSignUp()
        }

        return binding.root
    }

    private fun onButtonClickSigIn() {
        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    private fun onButtonClickSignUp() {
        val signup = Intent(activity, AuthActivity::class.java)
        signup.putExtra("page_request", 2)
        startActivity(signup)
    }
}
