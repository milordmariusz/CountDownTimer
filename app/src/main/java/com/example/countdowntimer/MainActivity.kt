package com.example.countdowntimer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private lateinit var textViewNumber1 : TextView
    private lateinit var textViewNumber2 : TextView
    private lateinit var textViewNumber3 : TextView
    private lateinit var textViewNumber4 : TextView

    private lateinit var buttonStart : Button
    private lateinit var buttonPause : Button
    private lateinit var buttonStop : Button

    private lateinit var countDownTimer : CountDownTimer
    private var startTime : Long = 0
    var timeLeftInMillis : Long = startTime

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewNumber1 = findViewById(R.id.TextView1)
        textViewNumber2 = findViewById(R.id.TextView2)
        textViewNumber3 = findViewById(R.id.TextView3)
        textViewNumber4 = findViewById(R.id.TextView4)

        buttonStart = findViewById(R.id.startButton)
        buttonPause = findViewById(R.id.pauseButton)
        buttonStop = findViewById(R.id.stopButton)

        val buttonNumberAdd1 = findViewById<Button>(R.id.NumberAdd1)
        val buttonNumberAdd2 = findViewById<Button>(R.id.NumberAdd2)
        val buttonNumberAdd3 = findViewById<Button>(R.id.NumberAdd3)
        val buttonNumberAdd4 = findViewById<Button>(R.id.NumberAdd4)

        val buttonNumberSub1 = findViewById<Button>(R.id.NumberSub1)
        val buttonNumberSub2 = findViewById<Button>(R.id.NumberSub2)
        val buttonNumberSub3 = findViewById<Button>(R.id.NumberSub3)
        val buttonNumberSub4 = findViewById<Button>(R.id.NumberSub4)

        buttonStart.setOnClickListener {
            startTimer()
        }
        buttonPause.setOnClickListener {
            pauseTimer()
        }
        buttonStop.setOnClickListener {
            stopTimer()
        }

        buttonNumberAdd1.setOnClickListener {
            if (textViewNumber1.text == "9") {
                textViewNumber1.text = "0"
            } else {
                textViewNumber1.text = (parseInt(textViewNumber1.text as String) + 1).toString()
            }
            updateButtons()
        }
        buttonNumberAdd2.setOnClickListener {
            if (textViewNumber2.text == "9") {
                textViewNumber2.text = "0"
            } else {
                textViewNumber2.text = (parseInt(textViewNumber2.text as String) + 1).toString()
            }
            updateButtons()
        }
        buttonNumberAdd3.setOnClickListener {
            if (textViewNumber3.text == "5") {
                textViewNumber3.text = "0"
            } else {
                textViewNumber3.text = (parseInt(textViewNumber3.text as String) + 1).toString()
            }
            updateButtons()
        }
        buttonNumberAdd4.setOnClickListener {
            if (textViewNumber4.text == "9") {
                textViewNumber4.text = "0"
            } else {
                textViewNumber4.text = (parseInt(textViewNumber4.text as String) + 1).toString()
            }
            updateButtons()
        }

        buttonNumberSub1.setOnClickListener {
            if (textViewNumber1.text == "0") {
                textViewNumber1.text = "9"
            } else {
                textViewNumber1.text = (parseInt(textViewNumber1.text as String) - 1).toString()
            }
            updateButtons()
        }
        buttonNumberSub2.setOnClickListener {
            if (textViewNumber2.text == "0") {
                textViewNumber2.text = "9"
            } else {
                textViewNumber2.text = (parseInt(textViewNumber2.text as String) - 1).toString()
            }
            updateButtons()
        }
        buttonNumberSub3.setOnClickListener {
            if (textViewNumber3.text == "0") {
                textViewNumber3.text = "5"
            } else {
                textViewNumber3.text = (parseInt(textViewNumber3.text as String) - 1).toString()
            }
            updateButtons()
        }
        buttonNumberSub4.setOnClickListener {
            if (textViewNumber4.text == "0") {
                textViewNumber4.text = "9"
            } else {
                textViewNumber4.text = (parseInt(textViewNumber4.text as String) - 1).toString()
            }
            updateButtons()
        }

        updateButtons()
        updateCountDownText()
    }

    private fun updateButtons() {
        buttonStart.isEnabled = !isTimeGreaterThanZero()
    }

    private fun isTimeGreaterThanZero(): Boolean {
        return textViewNumber1.text == "0" &&
                textViewNumber2.text == "0" &&
                textViewNumber3.text == "0" &&
                textViewNumber4.text == "0"
    }

    private fun startTimer() {
        timeLeftInMillis = countTimeToMillis()
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished : Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                buttonStart.isEnabled = true
            }
        }.start()

        buttonStart.isEnabled = false
    }

    private fun updateCountDownText() {
        val minutes : Int = ((timeLeftInMillis / 1000) / 60).toInt()
        val seconds : Int = ((timeLeftInMillis / 1000) % 60).toInt()

        textViewNumber1.text = (minutes / 10).toString()
        textViewNumber2.text = (minutes % 10).toString()
        textViewNumber3.text = (seconds / 10).toString()
        textViewNumber4.text = (seconds % 10).toString()
    }

    private fun countTimeToMillis(): Long {
        var  timeToMillis : Long = 0
        timeToMillis += (textViewNumber1.text as String).toLong() * 600000
        timeToMillis += (textViewNumber2.text as String).toLong() * 60000
        timeToMillis += (textViewNumber3.text as String).toLong() * 10000
        timeToMillis += (textViewNumber4.text as String).toLong() * 1000
        return timeToMillis
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        buttonStart.isEnabled = true
    }

    private fun stopTimer() {
        countDownTimer.cancel()
        timeLeftInMillis = startTime
        updateCountDownText()
        buttonStart.isEnabled = true
    }
}