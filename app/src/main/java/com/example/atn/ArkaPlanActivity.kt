package com.example.atn

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ArkaPlanActivity : AppCompatActivity() {

    private lateinit var rootLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arka_plan)

        rootLayout = findViewById(R.id.rootLayout)

        val btnBlue = findViewById<Button>(R.id.btnBlue)
        val btnPeach = findViewById<Button>(R.id.btnPeach)
        val btnSkin = findViewById<Button>(R.id.btnSkin)

        // Renkler
        btnBlue.setOnClickListener {
            AppPreferences.setString(this, "background_color", "#CCEFFF")
            applyBackgroundColor("#CCEFFF")
        }

        btnPeach.setOnClickListener {
            AppPreferences.setString(this, "background_color", "#FFDAB9") // Peach
            applyBackgroundColor("#FFDAB9")
        }

        btnSkin.setOnClickListener {
            AppPreferences.setString(this, "background_color", "#FFE4C4") // Skin
            applyBackgroundColor("#FFE4C4")
        }

        // Sayfa açıldığında mevcut rengi uygula
        val savedColor = AppPreferences.getString(this, "background_color", "#CCEFFF")
        applyBackgroundColor(savedColor)
    }

    private fun applyBackgroundColor(hex: String) {
        rootLayout.setBackgroundColor(Color.parseColor(hex))
    }
}
