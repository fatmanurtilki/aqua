package com.example.atn

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class DilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dil)

        val btnTurkce = findViewById<Button>(R.id.btnTurkce)
        val btnIngilizce = findViewById<Button>(R.id.btnIngilizce)

        btnTurkce.setOnClickListener {
            AppPreferences.setString(this, "lang", "tr")
            setLocale("tr")
        }

        btnIngilizce.setOnClickListener {
            AppPreferences.setString(this, "lang", "en")
            setLocale("en")
        }

        // Uygulama açıldığında kayıtlı dili uygula
        val currentLang = AppPreferences.getString(this, "lang", "tr")
        setLocale(currentLang)
    }

    private fun setLocale(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        Toast.makeText(this, "Dil değiştirildi: ${langCode.uppercase()}", Toast.LENGTH_SHORT).show()
        recreate() // sayfayı yeniden başlat
    }
}
