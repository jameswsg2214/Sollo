package com.example.furniture.loginActivity.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.furniture.R
import com.example.furniture.databinding.LoginscreenactivityBinding
import com.example.furniture.loginActivity.viewModel.LoginViewModel
import com.example.furniture.loginActivity.viewModel.LoginViewModelFactory

class LoginScreenActivity:AppCompatActivity() {

    var loginActivityBinding:LoginscreenactivityBinding?=null

    var loginViewModel:LoginViewModel?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        loginActivityBinding=DataBindingUtil.setContentView(this, R.layout.loginscreenactivity)

        loginViewModel=LoginViewModelFactory(application).create((LoginViewModel::class.java))

        loginActivityBinding!!.lifecycleOwner = this

        loginActivityBinding?.loginViewModel = loginViewModel



    }
}