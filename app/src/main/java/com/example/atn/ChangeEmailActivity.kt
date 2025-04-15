package com.example.atn

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangeEmailActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var saveButton: Button
    private val userEmail = "demo@mail.com" // Gerçek login'den alınmalı

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)

        emailInput = findViewById(R.id.newEmailInput)
        saveButton = findViewById(R.id.saveEmailButton)

        saveButton.setOnClickListener {
            val newEmail = emailInput.text.toString()
            if (newEmail.isNotEmpty()) {
                val db = DatabaseHelper(this)
                db.writableDatabase.execSQL("UPDATE users SET email = '$newEmail' WHERE email = '$userEmail'")
                Toast.makeText(this, "Email güncellendi!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Lütfen yeni e-mail girin.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
