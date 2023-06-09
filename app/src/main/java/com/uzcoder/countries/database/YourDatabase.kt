package com.uzcoder.countries.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uzcoder.countries.database.dao.SaveDaoInterface
import com.uzcoder.countries.database.entity.SaveData


@Database(
    entities = [SaveData::class], // Tell the database the entries will hold data of this type
    version = 1
)
@TypeConverters(HobbiesTypeConverter::class)
abstract class YourDatabase : RoomDatabase() {

    abstract fun getYourDao(): SaveDaoInterface
}