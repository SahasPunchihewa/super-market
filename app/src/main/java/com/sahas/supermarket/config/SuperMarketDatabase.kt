package com.sahas.supermarket.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahas.supermarket.model.Product
import com.sahas.supermarket.repositories.ProductDao

@Database(entities = [Product::class], version = 1)
abstract class SuperMarketDatabase : RoomDatabase() {
    abstract fun ProductDao(): ProductDao
}
