package com.example.furniture.dataBase.DAO

import androidx.room.*
import com.example.furniture.dataBase.entity.FurnirureList
import com.example.furniture.dataBase.entity.UserDetails


@Dao
interface FurnitureMasterDao {

    @Query("SELECT * FROM UserDetails where MobileNo=:Number order by Id desc")
    fun checkData(Number: String): Boolean

    @Query("SELECT * FROM UserDetails where MobileNo=:Number order by Id desc")
    fun getUserData(Number: String): UserDetails


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(vararg todo: UserDetails)


    @Update
    fun updateUserDetails(vararg todos: UserDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFurnirureDetails(vararg todo: FurnirureList)



    @Query("SELECT * FROM FurnirureList where userId=:userid  order by Id desc")
    fun getAllFurniture(userid: String): List<FurnirureList>


    @Query("delete from FurnirureList where Id=:Id")
    fun deleteIteem(Id: Long)






}