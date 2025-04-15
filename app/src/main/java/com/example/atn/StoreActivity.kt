package com.example.atn

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class StoreActivity : AppCompatActivity() {

    private val fishData = listOf(
        Fish("Beta Balığı", R.drawable.beta_balik, 1500),
        Fish("Melek Balığı", R.drawable.melek_balik, 2500),
        Fish("Cüze Vatoz Balığı", R.drawable.vatoz_balik, 1200),
        Fish("Moli Balığı", R.drawable.moli_balik, 1000),
        Fish("Papağan Balığı", R.drawable.papagan_balik, 1000),
        Fish("Astronot Balığı", R.drawable.astronot_balik, 1800),
        Fish("Palyaço Balığı", R.drawable.palyaco_balik, 1100),
        Fish("Labio Balığı", R.drawable.labio_balik, 200),
        Fish("Diskus Balığı", R.drawable.diskus_balik, 2200),
        Fish("Çizgili Yılan Balığı", R.drawable.yilan_balik, 1000)
    )


    private lateinit var fishListLayout: LinearLayout
    private lateinit var db: DatabaseHelper
    private var currentUser = "demo@mail.com" // Giriş yapan kullanıcı

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        db = DatabaseHelper(this)
        fishListLayout = findViewById(R.id.fishList)

        showFishList()
    }

    private fun showFishList() {
        val inflater = layoutInflater
        val userCoins = db.getPuan(currentUser)

        for (fish in fishData) {
            val item = inflater.inflate(R.layout.item_fish_store, null)

            val img = item.findViewById<ImageView>(R.id.fishImage)
            val name = item.findViewById<TextView>(R.id.fishName)
            val price = item.findViewById<TextView>(R.id.fishPrice)
            val buyBtn = item.findViewById<Button>(R.id.buyButton)

            img.setImageResource(fish.imageRes)
            name.text = fish.name
            price.text = "${fish.price} 🪙"

            // Satın alma kontrolü
            if (userCoins < fish.price) {
                buyBtn.isEnabled = false
                buyBtn.text = "Yetersiz"
            }

            buyBtn.setOnClickListener {
                val result = db.buyFish(currentUser, fish.name, fish.price)
                if (result) {
                    Toast.makeText(this, "${fish.name} satın alındı!", Toast.LENGTH_SHORT).show()
                    recreate()
                } else {
                    Toast.makeText(this, "Yetersiz coin!", Toast.LENGTH_SHORT).show()
                }
            }

            fishListLayout.addView(item)
        }
    }
}
