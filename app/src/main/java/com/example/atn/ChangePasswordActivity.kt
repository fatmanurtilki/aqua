package com.example.atn

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var newPasswordInput: EditText
    private lateinit var savePasswordButton: Button
    private val userEmail = "demo@mail.com" // Giriş yapmış kullanıcıdan alınmalı

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        newPasswordInput = findViewById(R.id.newPasswordInput)
        savePasswordButton = findViewById(R.id.savePasswordButton)

        savePasswordButton.setOnClickListener {
            val newPassword = newPasswordInput.text.toString()
            if (newPassword.isNotEmpty()) {
                val db = DatabaseHelper(this)
                db.writableDatabase.execSQL("UPDATE users SET password = '$newPassword' WHERE email = '$userEmail'")
                Toast.makeText(this, "Şifre güncellendi!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Lütfen yeni bir şifre girin.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
