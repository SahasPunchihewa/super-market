package com.sahas.supermarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.room.Room
import com.sahas.supermarket.config.SuperMarketDatabase
import com.sahas.supermarket.model.Product
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DBViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbview)

        val db = Room.databaseBuilder(
            this, SuperMarketDatabase::class.java,
            "superMarketDB"
        ).build()
        val productDao = db.ProductDao()

        val nameTxt = findViewById<TextView>(R.id.List)

        nameTxt.text = ""
        runBlocking {
            launch {
                val products: List<Product> = productDao.getAll()
                for (product in products) {
                    nameTxt.append("\n ${product.name} \t:\t ${product.price}")
                }
            }
        }
    }
}