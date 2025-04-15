package com.example.atn

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class IstatistikActivity : AppCompatActivity() {

    private lateinit var fishVisual: ImageView
    private lateinit var grafikView: ImageView
    private lateinit var db: DatabaseHelper
    private var email: String = "demo@mail.com" // Gerçek kullanıcı email'i alınabilir

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_istatistik)

        fishVisual = findViewById(R.id.fishVisual)
        grafikView = findViewById(R.id.grafikView)
        db = DatabaseHelper(this)

        // Filtre butonları
        findViewById<Button>(R.id.btnGun).setOnClickListener {
            gosterVeri("gun")
        }
        findViewById<Button>(R.id.btnHafta).setOnClickListener {
            gosterVeri("hafta")
        }
        findViewById<Button>(R.id.btnAy).setOnClickListener {
            gosterVeri("ay")
        }
        findViewById<Button>(R.id.btnYil).setOnClickListener {
            gosterVeri("yil")
        }

        gosterVeri("gun") // Varsayılan
    }

    private fun gosterVeri(zaman: String) {
        val toplamDakika = db.getDakikaByTime(email, zaman)
        val puan = (toplamDakika / 120.0 * 100).toInt()
        val balikSayisi = puan / 400

        Toast.makeText(this, "Puan: $puan, Balık: $balikSayisi", Toast.LENGTH_SHORT).show()

        // Balık görselini puana göre değiştir
        when (balikSayisi) {
            0 -> fishVisual.setImageResource(R.drawable.ocean)
            1 -> fishVisual.setImageResource(R.drawable.beta_balik)
            2 -> fishVisual.setImageResource(R.drawable.melek_balik)
            3 -> fishVisual.setImageResource(R.drawable.diskus_balik)
            else -> fishVisual.setImageResource(R.drawable.astronot_balik)
        }

        // Grafik kısmı örnek: static olarak görsel
        grafikView.setImageResource(R.drawable.turkograf)
    }
}
