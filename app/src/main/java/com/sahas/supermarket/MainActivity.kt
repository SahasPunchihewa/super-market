package com.sahas.supermarket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.sahas.supermarket.config.SuperMarketDatabase
import com.sahas.supermarket.model.Product
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this, SuperMarketDatabase::class.java,
            "superMarketDB"
        ).build()
        val productDao = db.ProductDao()

        val nameTxt = findViewById<EditText>(R.id.Name)
        val priceTxt = findViewById<EditText>(R.id.Price)
        val saveBtn = findViewById<Button>(R.id.Save)
        val loadBtn = findViewById<Button>(R.id.Load)

        saveBtn.setOnClickListener {
            if (nameTxt.text.toString() != "" && priceTxt.text.toString().toInt() != 0) {
                runBlocking {
                    launch {
                        productDao.insertProduct(Product(nameTxt.text.toString(), priceTxt.text.toString().toInt()))
                        nameTxt.text.clear()
                        priceTxt.text.clear()
                    }
                }
            }
        }

        loadBtn.setOnClickListener {
            val dbIntent = Intent(this, DBViewActivity::class.java)
            startActivity(dbIntent)
        }
    }
}