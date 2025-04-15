package com.example.atn

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

object ToolbarUtils {
    fun setupToolbar(activity: Activity, rootView: View, email: String) {
        val db = DatabaseHelper(activity)
        val puan = db.getPuan(email)
        rootView.findViewById<TextView>(R.id.toolbarPuan).text = puan.toString()

        rootView.findViewById<ImageView>(R.id.menuAkvaryum).setOnClickListener {
            activity.startActivity(Intent(activity, AkvaryumActivity::class.java))
        }

        rootView.findViewById<ImageView>(R.id.menuIstatistik).setOnClickListener {
            activity.startActivity(Intent(activity, IstatistikActivity::class.java))
        }

        rootView.findViewById<ImageView>(R.id.menuRozet).setOnClickListener {
            activity.startActivity(Intent(activity, RozetActivity::class.java))
        }

        rootView.findViewById<ImageView>(R.id.menuStore).setOnClickListener {
            activity.startActivity(Intent(activity, StoreActivity::class.java))
        }

        rootView.findViewById<ImageView>(R.id.menuHesap).setOnClickListener {
            activity.startActivity(Intent(activity, HesapActivity::class.java))
        }

        rootView.findViewById<ImageView>(R.id.menuAyarlar).setOnClickListener {
            activity.startActivity(Intent(activity, AyarlarActivity::class.java))
        }
    }
}
