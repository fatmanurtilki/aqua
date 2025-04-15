package com.example.atn

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserData.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE, password TEXT)")
        db.execSQL("CREATE TABLE puanlar (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, dakikalar INTEGER DEFAULT 0)")
        db.execSQL("CREATE TABLE baliklar (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        db.execSQL("DROP TABLE IF EXISTS puanlar")
        db.execSQL("DROP TABLE IF EXISTS baliklar")
        onCreate(db)
    }

    fun insertUser(email: String, password: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("email", email)
            put("password", password)
        }
        val result = db.insert("users", null, values)
        return result != -1L
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM users WHERE email = ? AND password = ?",
            arrayOf(email, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun addMinutesAndScore(email: String, dakikalar: Int, puan: Int) {
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM puanlar WHERE email = ?", arrayOf(email))

        if (cursor.moveToFirst()) {
            val eskiDakika = cursor.getInt(cursor.getColumnIndexOrThrow("dakikalar"))
            val yeniDakika = eskiDakika + dakikalar
            db.execSQL("UPDATE puanlar SET dakikalar = $yeniDakika WHERE email = '$email'")
        } else {
            val values = ContentValues()
            values.put("email", email)
            values.put("dakikalar", dakikalar)
            db.insert("puanlar", null, values)
        }

        cursor.close()
    }

    fun getDakikaByTime(email: String, filtre: String): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT dakikalar FROM puanlar WHERE email = ?", arrayOf(email))
        var toplam = 0
        if (cursor.moveToFirst()) {
            toplam = cursor.getInt(0)
        }
        cursor.close()
        return toplam
    }

    fun getPuan(email: String): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT dakikalar FROM puanlar WHERE email = ?", arrayOf(email))
        var dakika = 0
        if (cursor.moveToFirst()) {
            dakika = cursor.getInt(0)
        }
        cursor.close()
        return (dakika / 120.0 * 100).toInt()
    }

    fun buyFish(email: String, fishName: String, cost: Int): Boolean {
        val currentPuan = getPuan(email)
        if (currentPuan >= cost) {
            val db = this.writableDatabase
            db.execSQL("INSERT INTO baliklar (email, name) VALUES ('$email', '$fishName')")
            val kalanDakika = ((currentPuan - cost) / 100.0 * 120).toInt()
            db.execSQL("UPDATE puanlar SET dakikalar = $kalanDakika WHERE email = '$email'")
            return true
        }
        return false
    }
}
