package com.example.atn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SayacActivity : AppCompatActivity() {

    private lateinit var motivationText: TextView
    private lateinit var btnVazgec: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sayac)

        motivationText = findViewById(R.id.motivationText)
        btnVazgec = findViewById(R.id.btnVazgec)

        btnVazgec.setOnClickListener {
            Toast.makeText(this, "Sayaç durduruldu, başlangıç ekranına dönülüyor.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AkvaryumActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
