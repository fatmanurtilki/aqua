package com.example.atn

import android.animation.ValueAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.airbnb.lottie.LottieAnimationView
import com.example.projetaslak.databinding.ActivityAkvaryumBinding

class AkvaryumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAkvaryumBinding
    private lateinit var sharedPreferences: SharedPreferences // sharedPreferences değişkeni
    private var totalTimeInMillis: Long = 0
    private var timeLeftInMillis: Long = 0
    private var timer: CountDownTimer? = null
    private var isRunning = false
    private var currentMinutes = 25
    private var progressAnimator: ValueAnimator? = null
    private var mediaPlayer: MediaPlayer? = null
    private var selectedSound = "forest"
    private lateinit var animationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAkvaryumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar'ı set et
        setSupportActionBar(binding.toolbar) // Set the toolbar as the action bar

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        selectedSound = sharedPreferences.getString("selectedSound", "forest") ?: "forest"

        animationView = binding.animationView
        animationView.setAnimation("fish.json")

        setupButtons()
        setupSoundSpinner()
        updateTimerText()
        updateProgressCircle()
    }

    private fun setupButtons() {
        binding.btnStartPause.setOnClickListener {
            if (isRunning) pauseTimer() else startTimer()
        }

        binding.btnReset.setOnClickListener {
            resetTimer()
        }

        binding.etMinutes.setText(currentMinutes.toString())
    }

    private fun setupSoundSpinner() {
        val soundOptions = arrayOf("forest", "rain", "sea")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, soundOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.soundSpinner.adapter = adapter

        binding.soundSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedSound = soundOptions[position]
                sharedPreferences.edit().putString("selectedSound", selectedSound).apply()
                if (isRunning) {
                    startSound()
                } else {
                    stopSound()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val selectedIndex = soundOptions.indexOf(selectedSound)
        if (selectedIndex != -1) {
            binding.soundSpinner.setSelection(selectedIndex)
        }
    }

    private fun startTimer() {
        if (timeLeftInMillis <= 0) {
            currentMinutes = binding.etMinutes.text.toString().toIntOrNull() ?: 25
            totalTimeInMillis = currentMinutes * 60 * 1000L
            timeLeftInMillis = totalTimeInMillis
        }

        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
                updateProgressCircle()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateTimerText()
                updateProgressCircle()
                isRunning = false
                binding.btnStartPause.text = "Başlat"
                binding.circleProgress.visibility = View.INVISIBLE
                stopSound()
                animationView.pauseAnimation()
            }
        }.start()

        isRunning = true
        binding.btnStartPause.text = "Durdur"
        binding.circleProgress.visibility = View.VISIBLE
        startSound()
        animationView.playAnimation()
    }

    private fun pauseTimer() {
        timer?.cancel()
        isRunning = false
        binding.btnStartPause.text = "Başlat"
        binding.circleProgress.visibility = View.INVISIBLE
        stopSound()
        animationView.pauseAnimation()
    }

    private fun resetTimer() {
        timer?.cancel()
        currentMinutes = binding.etMinutes.text.toString().toIntOrNull() ?: 25
        totalTimeInMillis = currentMinutes * 60 * 1000L
        timeLeftInMillis = totalTimeInMillis
        updateTimerText()
        updateProgressCircle()
        isRunning = false
        binding.btnStartPause.text = "Başlat"
        binding.circleProgress.visibility = View.VISIBLE
        stopSound()
        animationView.pauseAnimation()
        animationView.progress = 0f
    }

    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        binding.tvTimer.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun updateProgressCircle() {
        val progress = (timeLeftInMillis.toFloat() / totalTimeInMillis.toFloat()) * 360
        progressAnimator?.cancel()
        progressAnimator = ValueAnimator.ofFloat(binding.circleProgress.rotation, progress - 90).apply {
            interpolator = LinearInterpolator()
            duration = 500
            addUpdateListener { animation ->
                binding.circleProgress.rotation = animation.animatedValue as Float
            }
            start()
        }
    }

    private fun startSound() {
        val soundResId = when (selectedSound) {
            "forest" -> R.raw.forest
            "rain" -> R.raw.forest
            "sea" -> R.raw.forest
            else -> R.raw.forest
        }

        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    private fun stopSound() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        progressAnimator?.cancel()
        stopSound()
    }
}
