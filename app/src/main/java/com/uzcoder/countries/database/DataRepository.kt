package com.uzcoder.countries.database

import com.uzcoder.countries.database.dao.SaveDaoInterface
import com.uzcoder.countries.database.entity.SaveData
import javax.inject.Inject

class DataRepository @Inject constructor(private val dao : SaveDaoInterface) {

    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: SaveData) : Long {
        return dao.insertSubscriber(subscriber)
    }

    suspend fun deleteAll() : Int {
        return dao.bDeleteAll()
    }
}