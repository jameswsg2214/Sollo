package com.example.furniture.mainPage.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.View
import androidx.annotation.UiThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.furniture.addFurniture.ui.AddFurnitureActivity
import com.example.furniture.data.AppPreferences
import com.example.furniture.dataBase.entity.FurnirureList
import com.example.furniture.utils.Constants
import com.example.furniture.dataBase.FurnitureDB


class MainPageViewModel(application: Application): AndroidViewModel(application) {

    var userLiveData: MutableLiveData<ArrayList<FurnirureList>>? = null
    var userArrayList: ArrayList<FurnirureList>? = ArrayList()
    var appPreferences: AppPreferences
    var FurnitureDb: FurnitureDB


    init {


        userLiveData = MutableLiveData()

      //  setData()

        FurnitureDb= FurnitureDB.getInstance(application)!!

        appPreferences = AppPreferences.getInstance(application, Constants.SHARE_PREFERENCE_NAME)!!


        AsyncTask.execute {
            try {
                val Alldata= FurnitureDb.furnitureMasterDao().getAllFurniture(appPreferences.getString(Constants.UserID)!!)

                userArrayList?.addAll(Alldata)

                userLiveData?.value = userArrayList


            } catch (e: Exception) {


            }
        }
    }

   fun setData() {

        AsyncTask.execute {
            try {
                val Alldata= FurnitureDb.furnitureMasterDao().getAllFurniture(appPreferences.getString(Constants.UserID)!!)

                userArrayList?.addAll(Alldata)

                userLiveData?.value = userArrayList



            } catch (e: Exception) {


            }
        }
    }

    fun getData(): ArrayList<FurnirureList>? {

        AsyncTask.execute {
            try {
                val Alldata= FurnitureDb.furnitureMasterDao().getAllFurniture(appPreferences.getString(Constants.UserID)!!)

                userArrayList?.clear()

                userArrayList?.addAll(Alldata)

                userLiveData?.value = userArrayList



            } catch (e: Exception) {


            }
        }

        return userArrayList
    }


    fun getUserMutableLiveData(): MutableLiveData<ArrayList<FurnirureList>>? {
        return userLiveData
    }




    fun OnClickAdd(view: View){


        val context: Context = view.context

        val intent = Intent(context, AddFurnitureActivity::class.java)

        context.startActivity(intent)


    }

    fun DeleteData(itemId: Long):ArrayList<FurnirureList>? {



        AsyncTask.execute {
            try {

                FurnitureDb.furnitureMasterDao().deleteIteem(itemId)


                val Alldata= FurnitureDb.furnitureMasterDao().getAllFurniture(appPreferences.getString(Constants.UserID)!!)

                userArrayList?.clear()

                userArrayList?.addAll(Alldata)

                userLiveData?.value = userArrayList



            } catch (e: Exception) {


            }
        }

        return userArrayList

    }


}