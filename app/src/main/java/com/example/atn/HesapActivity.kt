package com.example.atn

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HesapActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var editIcon: ImageView
    private val SELECT_PICTURE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hesap)

        profileImage = findViewById(R.id.profileImage)
        editIcon = findViewById(R.id.editIcon)

        editIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, SELECT_PICTURE)
        }

        findViewById<Button>(R.id.btnChangeEmail).setOnClickListener {
            // Mail değiştirme ekranı
            startActivity(Intent(this, ChangeEmailActivity::class.java))
        }

        findViewById<Button>(R.id.btnChangePassword).setOnClickListener {
            // Şifre değiştirme ekranı
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            val inputStream = contentResolver.openInputStream(selectedImage!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            profileImage.setImageBitmap(bitmap)
        }
    }
}
