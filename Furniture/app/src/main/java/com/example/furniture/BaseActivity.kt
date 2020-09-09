package com.example.furniture

import android.app.Application

class BaseActivity : Application() {

    init {

        instance = this

    }


    companion object{
        lateinit var instance:BaseActivity



    }



}