package com.example.atn

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ErisimActivity : AppCompatActivity() {

    private val apps = listOf(
        "TikTok", "Instagram", "Facebook", "Snapchat", "Pinterest",
        "Twitter", "Discord", "Twitch", "LinkedIn"
    )

    private val checkBoxes = mutableMapOf<String, CheckBox>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_erisim)

        for (app in apps) {
            val idName = "check$app"
            val resId = resources.getIdentifier(idName, "id", packageName)
            val checkbox = findViewById<CheckBox>(resId)
            checkBoxes[app] = checkbox

            // Kayıtlı ayarı yükle
            checkbox?.isChecked = AppPreferences.getBoolean(this, "block_$app", false)
        }

        val btnKaydet = findViewById<Button>(R.id.btnKaydet)
        btnKaydet.setOnClickListener {
            for ((app, checkbox) in checkBoxes) {
                AppPreferences.setBoolean(this, "block_$app", checkbox.isChecked)
            }
            Toast.makeText(this, "Seçimler kaydedildi!", Toast.LENGTH_SHORT).show()
        }
    }
}
