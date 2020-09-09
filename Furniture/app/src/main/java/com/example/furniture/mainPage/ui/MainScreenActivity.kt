package com.example.furniture.mainPage.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.furniture.R
import com.example.furniture.databinding.MainscreenBinding
import com.example.furniture.mainPage.viewModel.MainPageViewModel
import com.example.furniture.mainPage.viewModel.MainPageViewModelFactory


class MainScreenActivity:AppCompatActivity() {

    var mainActivityBinding:MainscreenBinding?=null

    var mainViewModel:MainPageViewModel?=null

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainActivityBinding=DataBindingUtil.setContentView(this, R.layout.mainscreen)

        mainViewModel=MainPageViewModelFactory(application).create((MainPageViewModel::class.java))

        mainActivityBinding!!.lifecycleOwner = this

        mainActivityBinding?.mainPageViewModel = mainViewModel

        setSupportActionBar(mainActivityBinding!!.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        recyclerView = mainActivityBinding?.recycler!!



        val linearLayoutManager = LinearLayoutManager(Activity())
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        mainActivityBinding?.recycler!!.layoutManager = linearLayoutManager
        var ListAdapter = RecyclerViewAdapter(this, ArrayList())
        mainActivityBinding?.recycler!!?.adapter = ListAdapter



        ListAdapter.setData(mainViewModel?.getData())

        ListAdapter.setOnItemClickListener(object :RecyclerViewAdapter.OnItemClickListener{
            override fun onClick(ItemId: Long) {

                val builder = AlertDialog.Builder(this@MainScreenActivity)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->

                        ListAdapter.setData(mainViewModel?.DeleteData(ItemId))


                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()


            }
        })



    }



}