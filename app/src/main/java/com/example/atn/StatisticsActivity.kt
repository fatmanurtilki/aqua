package com.example.atn

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.atn.databinding.ActivityStatisticsBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class StatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticsBinding
    private lateinit var barChart: BarChart
    private var selectedTabId = R.id.tabGun

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar ayarları
        setupToolbar()

        // Grafik ayarları
        barChart = binding.barChart
        setupChart()

        // Tab seçimleri
        setupTabs()

        // Başlangıç verilerini yükle
        loadDataForTab(selectedTabId)
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Menü ikonu tıklama
        binding.menuIcon.setOnClickListener {
            openOptionsMenu()
        }
    }

    private fun setupChart() {
        with(barChart) {
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            description.isEnabled = false
            legend.isEnabled = false
            setPinchZoom(false)
            setDrawGridBackground(false)

            // X ekseni ayarları
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                granularity = 1f
                valueFormatter = object : IndexAxisValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return when (value.toInt()) {
                            0 -> "Pzt"
                            1 -> "Sal"
                            2 -> "Çar"
                            3 -> "Per"
                            4 -> "Cum"
                            5 -> "Cmt"
                            6 -> "Paz"
                            else -> ""
                        }
                    }
                }
            }

            // Y ekseni ayarları
            axisLeft.apply {
                setDrawGridLines(true)
                axisMinimum = 0f
            }
            axisRight.isEnabled = false
        }
    }

    private fun setupTabs() {
        val tabs = listOf(
            binding.tabGun,
            binding.tabHafta,
            binding.tabAy,
            binding.tabYil
        )

        tabs.forEach { tab ->
            tab.setOnClickListener {
                selectTab(it as TextView)
                loadDataForTab(it.id)
            }
        }
    }

    private fun selectTab(selectedTab: TextView) {
        listOf(binding.tabGun, binding.tabHafta, binding.tabAy, binding.tabYil).forEach {
            it.setBackgroundResource(if (it == selectedTab) R.drawable.tab_selected else android.R.color.transparent)
            it.setTextColor(Color.parseColor(if (it == selectedTab) "#FFFFFF" else "#000000"))
        }
        selectedTabId = selectedTab.id
    }

    private fun loadDataForTab(tabId: Int) {
        when (tabId) {
            R.id.tabGun -> showDailyData()
            R.id.tabHafta -> showWeeklyData()
            R.id.tabAy -> showMonthlyData()
            R.id.tabYil -> showYearlyData()
        }
    }

    private fun showDailyData() {
        val entries = listOf(
            BarEntry(0f, 8f),
            BarEntry(1f, 6f),
            BarEntry(2f, 4f),
            BarEntry(3f, 2f)
        )
        updateChart(entries, "Günlük Veriler")
        binding.noDataText.visibility = View.GONE
    }

    private fun showWeeklyData() {
        val entries = listOf(
            BarEntry(0f, 45f),
            BarEntry(1f, 30f),
            BarEntry(2f, 25f),
            BarEntry(3f, 40f)
        )
        updateChart(entries, "Haftalık Veriler")
        binding.noDataText.visibility = View.GONE
    }

    private fun showMonthlyData() {
        // Örnek: Veri yok durumu
        binding.barChart.visibility = View.GONE
        binding.noDataText.visibility = View.VISIBLE
    }

    private fun showYearlyData() {
        val entries = listOf(
            BarEntry(0f, 120f),
            BarEntry(1f, 240f),
            BarEntry(2f, 180f)
        )
        updateChart(entries, "Yıllık Veriler")
        binding.noDataText.visibility = View.GONE
    }

    private fun updateChart(entries: List<BarEntry>, label: String) {
        binding.barChart.visibility = View.VISIBLE

        val dataSet = BarDataSet(entries, label).apply {
            color = Color.parseColor("#4CAF50")
            valueTextColor = Color.BLACK
            valueTextSize = 12f
        }

        val data = BarData(dataSet).apply {
            barWidth = 0.5f
        }

        with(barChart) {
            this.data = data
            animateY(1000)
            invalidate()
        }
    }

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

    fun onImageClick(item: MenuItem): Boolean {
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

    private fun showHelpDialog() {
        // Basit bir help dialog gösterimi
        android.app.AlertDialog.Builder(this)
            .setTitle("Yardım")
            .setMessage("Gün: Son 24 saat\nHafta: Son 7 gün\nAy: Son 30 gün\nYıl: Son 12 ay")
            .setPositiveButton("Tamam", null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        barChart.clear()
    }
}