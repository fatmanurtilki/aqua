package com.example.atn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AyarlarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayarlar)

        val btnArkaPlan = findViewById<Button>(R.id.btnArkaPlan)
        val btnErisim = findViewById<Button>(R.id.btnErisim)
        val btnDil = findViewById<Button>(R.id.btnDil)
        val btnSes = findViewById<Button>(R.id.btnSes)

        btnArkaPlan.setOnClickListener {
            startActivity(Intent(this, ArkaPlanActivity::class.java))
        }

        btnErisim.setOnClickListener {
            startActivity(Intent(this, ErisimActivity::class.java))
        }

        btnDil.setOnClickListener {
            startActivity(Intent(this, DilActivity::class.java))
        }

        btnSes.setOnClickListener {
            startActivity(Intent(this, SesActivity::class.java))
        }
    }
}
