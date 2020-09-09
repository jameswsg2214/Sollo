package com.example.furniture.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.furniture.dataBase.DAO.FurnitureMasterDao
import com.example.furniture.dataBase.entity.DateConverter
import com.example.furniture.dataBase.entity.FurnirureList
import com.example.furniture.dataBase.entity.UserDetails
import com.example.furniture.utils.Constants

//This annotation to tell room what is the entity/table of the database
@Database(
    entities = [
        UserDetails::class, FurnirureList::class
    ], version = 1, exportSchema = false
)

@TypeConverters(DateConverter::class)
abstract class FurnitureDB : RoomDatabase() {

    abstract fun furnitureMasterDao(): FurnitureMasterDao

    companion object {
        @Volatile
        private var INSTANCE: FurnitureDB? = null

        fun getInstance(context: Context): FurnitureDB? {
            if (INSTANCE == null) {
                synchronized(FurnitureDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FurnitureDB::class.java, Constants.DB_NAME
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}