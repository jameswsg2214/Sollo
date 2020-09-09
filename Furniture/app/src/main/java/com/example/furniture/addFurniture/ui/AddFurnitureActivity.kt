package com.example.furniture.addFurniture.ui

import android.Manifest
import android.Manifest.permission.CAMERA
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentUris
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.furniture.R
import com.example.furniture.addFurniture.viewModel.AddFurnitureViewModel
import com.example.furniture.addFurniture.viewModel.AddFurnitureViewModelFactory
import com.example.furniture.databinding.AddFurnitureActivityBinding
import com.example.furniture.databinding.LoginscreenactivityBinding
import com.example.furniture.databinding.MainscreenBinding
import com.example.furniture.loginActivity.viewModel.LoginViewModel
import com.example.furniture.loginActivity.viewModel.LoginViewModelFactory
import com.example.furniture.mainPage.viewModel.MainPageViewModel
import com.example.furniture.mainPage.viewModel.MainPageViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddFurnitureActivity:AppCompatActivity() {

    var addFurnitureActivityBinding:AddFurnitureActivityBinding?=null

    var addFurnitureViewModel:AddFurnitureViewModel?=null



    val items = arrayOf<CharSequence>(
        "Take Photo", "Choose from Library",
        "Cancel"
    )


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        addFurnitureActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.add_furniture_activity)

        addFurnitureViewModel =
            AddFurnitureViewModelFactory(application).create((AddFurnitureViewModel::class.java))

        addFurnitureActivityBinding!!.lifecycleOwner = this

        addFurnitureActivityBinding?.addFurnitureViewModel = addFurnitureViewModel

        setSupportActionBar(addFurnitureActivityBinding!!.toolbar)


        addFurnitureActivityBinding?.productImage?.setOnClickListener {

            //       RequestMultiplePermission()
        }

    }



}