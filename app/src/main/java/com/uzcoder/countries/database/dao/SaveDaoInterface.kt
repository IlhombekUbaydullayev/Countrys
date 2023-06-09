package com.uzcoder.countries.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uzcoder.countries.database.entity.SaveData

@Dao
interface SaveDaoInterface {
    @Insert
    suspend fun insertSubscriber(subscriber: SaveData): Long

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<SaveData>>

    @Query("DELETE FROM subscriber_data_table" )
    suspend fun bDeleteAll() : Int
}