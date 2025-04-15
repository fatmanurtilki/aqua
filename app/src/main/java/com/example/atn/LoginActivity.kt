package com.example.atn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DatabaseHelper(this)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signInButton = findViewById(R.id.signInButton)
        signUpButton = findViewById(R.id.signUpButton)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (dbHelper.checkUser(email, password)) {
                Toast.makeText(this, "Giriş başarılı!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AkvaryumActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Hatalı e-posta ya da şifre!", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (dbHelper.insertUser(email, password)) {
                Toast.makeText(this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AkvaryumActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Kayıt başarısız veya kullanıcı mevcut!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
