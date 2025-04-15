package com.example.atn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.atn.databinding.ActivityRozetBinding
class RozetActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_main -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.action_statistics -> {
                startActivity(Intent(this, StatisticsActivity::class.java))
                true
            }
            R.id.action_badges -> {
                startActivity(Intent(this, RozetActivity::class.java))
                true
            }
            R.id.action_store -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.action_hesap -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.menu_settings -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRozet() {
        // Bu fonksiyonda rozet durumlarını kontrol edip görsel değişiklikler yapabiliriz
        // Örneğin, kazanılmamış rozetleri gri yapmak gibi

        // SharedPreferences veya ViewModel'den kullanıcının rozet durumlarını al
        val earnedBadges = listOf("İnci Rozeti", "Denizatı Rozeti") // Örnek veri

        // Tüm rozetleri kontrol et ve kazanılmayanları gri yap
        val badgeNames = listOf(
            "İnci Rozeti",
            "Denizaltı Rozeti",
            "Dalga Rozeti",
            "Kaplumbağa Rozeti",
            "Denizatı Rozeti",
            "Deniz Yıldızı Rozeti"
        )

        badgeNames.forEach { badgeName ->
            if (!earnedBadges.contains(badgeName)) {
                // Bu rozet kazanılmamış, gri yap
                // Not: Bu örnekte TextView'ları ID olmadan bulmak zor,
                // pratikte her rozet için bir ID tanımlamak daha iyi olur
            }
        }
    }

    // Menü ikonuna tıklama işlemi (XML'deki ImageView için)
    private fun setupMenuButton() {
        // findViewById ile menü ikonunu bul ve tıklama dinleyicisi ekle
        // Örnek:
        // val menuButton = findViewById<ImageView>(R.id.menu_icon)
        // menuButton.setOnClickListener {
        //     // Menüyü aç
        // }
    }
}