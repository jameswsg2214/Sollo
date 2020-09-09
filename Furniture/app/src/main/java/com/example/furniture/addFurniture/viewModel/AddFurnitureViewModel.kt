package com.example.furniture.addFurniture.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.furniture.data.AppPreferences
import com.example.furniture.dataBase.entity.FurnirureList
import com.example.furniture.mainPage.ui.MainScreenActivity
import com.example.furniture.utils.Constants
import com.example.furniture.dataBase.FurnitureDB
import java.lang.Exception
import java.util.*


class AddFurnitureViewModel(application: Application): AndroidViewModel(application) {

    var Type:String=""

    lateinit var FurnitureDb: FurnitureDB


    var ProductName = MutableLiveData<String>()

    var ProductImage = MutableLiveData<String>()
    var ProductPrice = MutableLiveData<String>()
   var  ProductPriceDiscount = MutableLiveData<String>()

    var appPreferences:AppPreferences

    init {


        Type=""

        ProductName.value = ""
        ProductPrice.value = ""
        ProductPriceDiscount.value = ""

        ProductImage.value = ""
        FurnitureDb= FurnitureDB.getInstance(application)!!

        appPreferences = AppPreferences.getInstance(application, Constants.SHARE_PREFERENCE_NAME)!!

    }


    fun onSelectItem(
        parent: AdapterView<*>?,
        view: View?,
        pos: Int,
        id: Long
    ) {
        //pos                                 get selected item position
        //view.getText()                      get lable of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item
        //and other...


        Type = parent?.getAdapter()?.getItem(pos).toString()
    }


    fun OnClickBack(view: View){


        val context: Context = view.context

        val intent = Intent(context, MainScreenActivity::class.java)

        context.startActivity(intent)


    }



    fun OnClickSave(view: View){


        val furnitureData=FurnirureList(
            ProductName = ProductName.value,
            ProductPrice =  (ProductPrice .value?.toInt()!!- ProductPriceDiscount.value?.toInt()!!).toString(),
            ProductPriceDiscount = ProductPriceDiscount.value,
            ProductOriginalPrice=ProductPrice .value,
            ProductType = Type,
            UserId = appPreferences.getString(Constants.UserID),
            Created_date = Date(),
            Modified_date = Date()

        )


        AsyncTask.execute {


                try {

                    FurnitureDb.furnitureMasterDao().insertFurnirureDetails(furnitureData)

                        val context: Context = view.context

                        val intent = Intent(context, MainScreenActivity::class.java)

                        context.startActivity(intent)



                } catch (e: Exception) {


                }



        }


/*


        val context: Context = view.context

        val intent = Intent(context, MainScreenActivity::class.java)

        context.startActivity(intent)
*/


    }


}