package com.sahas.supermarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product(
    @PrimaryKey val name: String,
    val price: Int,
)