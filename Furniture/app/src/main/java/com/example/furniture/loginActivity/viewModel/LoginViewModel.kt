package com.example.furniture.loginActivity.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.furniture.data.AppPreferences
import com.example.furniture.dataBase.entity.UserDetails
import com.example.furniture.mainPage.ui.MainScreenActivity
import com.example.furniture.utils.Constants
import com.example.furniture.dataBase.FurnitureDB
import java.lang.Exception
import java.util.*


class LoginViewModel(application: Application): AndroidViewModel(application) {

    var number= MutableLiveData<String>()

    var numberEt= MutableLiveData<Int>()

    var numberBtn= MutableLiveData<Int>()

    var otpEt= MutableLiveData<Int>()

    var otp= MutableLiveData<String>()

    var otpBtn= MutableLiveData<Int>()

    var appPreferences:AppPreferences

    lateinit var FurnitureDb: FurnitureDB

    init {

        number.value=""

        otp.value=""

        numberBtn.value=0

        numberEt.value=0

        otpEt.value=8

        otpBtn.value=8

        FurnitureDb= FurnitureDB.getInstance(application)!!

        appPreferences = AppPreferences.getInstance(application, Constants.SHARE_PREFERENCE_NAME)!!

    }


    fun OnClickSendOTP(){


        if(number.value==""){

            Toast.makeText(getApplication(),"Please Enter Your Number " ,Toast.LENGTH_LONG).show()



        }

        else if(number.value?.length!! < 10){


            Toast.makeText(getApplication(),"Please Enter Valid number" ,Toast.LENGTH_LONG).show()



        }
        else{

            numberBtn.value=8

            numberEt.value=8

            otpEt.value=0

            otpBtn.value=0


        }
    }

    fun OnClickOTPVerifed(view:View){


        if(otp.value==""){

            Toast.makeText(getApplication(),"Please Enter Your OTP " ,Toast.LENGTH_LONG).show()

        }

        else if(otp.value?.length!! < 6){

            Toast.makeText(getApplication(),"Please Enter Valid OTP" ,Toast.LENGTH_LONG).show()

        }


        else{


            AsyncTask.execute {

                val checkData = FurnitureDb.furnitureMasterDao().checkData(number.value!!)

                if (checkData) {

                    var getUserData = FurnitureDb.furnitureMasterDao().getUserData(number.value!!)

                    val currentTime = Date()

                    getUserData.Modified_date = currentTime

                    appPreferences.saveString(Constants.UserID, getUserData.Id.toString())

                    try {

                        FurnitureDb.furnitureMasterDao().updateUserDetails(getUserData)


                        val context: Context = view.context

                        val intent = Intent(context, MainScreenActivity::class.java)

                        context.startActivity(intent)



                    } catch (e: Exception) {


                    }

                } else {

                    val currentTime = Date()

                    var insertData = UserDetails(
                        MobileNo = number.value!!,
                        Created_date = currentTime,
                        Modified_date = currentTime

                    )



                    try {

                        FurnitureDb.furnitureMasterDao().insertUserDetails(insertData)

                        val checkData = FurnitureDb.furnitureMasterDao().checkData(number.value!!)

                        if (checkData) {

                            var getUserData =
                                FurnitureDb.furnitureMasterDao().getUserData(number.value!!)

                            appPreferences.saveString(Constants.UserID, getUserData.Id.toString())

                            val context: Context = view.context

                            val intent = Intent(context, MainScreenActivity::class.java)

                            context.startActivity(intent)



                        }

                    } catch (e: Exception) {


                    }


                }


            }

        }
    }


}