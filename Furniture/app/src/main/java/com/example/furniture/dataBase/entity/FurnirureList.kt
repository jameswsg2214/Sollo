package com.example.furniture.dataBase.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "FurnirureList")
data class FurnirureList(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var Id: Long? = null,
    @ColumnInfo(name = "UserId") var UserId: String? = null,
    @ColumnInfo(name = "ProductName") var ProductName: String? = null,
    @ColumnInfo(name = "ProductType") var ProductType: String? = null,
    @ColumnInfo(name = "ProductPrice") var ProductPrice: String? = null,
    @ColumnInfo(name = "ProductOriginalPrice") var ProductOriginalPrice: String? = null,
    @ColumnInfo(name = "ProductPriceDiscount") var ProductPriceDiscount: String? = null,
    @ColumnInfo(name = "ImagePath") var ImagePath: String? = null,
    @ColumnInfo(name = "Created_date") var Created_date: Date? = null,
    @ColumnInfo(name = "Modified_date") var Modified_date: Date? = null
) : Parcelable


