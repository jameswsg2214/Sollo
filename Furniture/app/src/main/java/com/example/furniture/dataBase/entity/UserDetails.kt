package com.example.furniture.dataBase.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "UserDetails")
data class UserDetails(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var Id: Long? = null,
    @ColumnInfo(name = "MobileNo") var MobileNo: String? = null,
    @ColumnInfo(name = "Created_date") var Created_date: Date? = null,
    @ColumnInfo(name = "Modified_date") var Modified_date: Date? = null
) : Parcelable


