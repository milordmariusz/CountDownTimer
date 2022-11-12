package com.example.countdowntimer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    lateinit var textViewNumber1 : TextView
    lateinit var textViewNumber2 : TextView
    lateinit var textViewNumber3 : TextView
    lateinit var textViewNumber4 : TextView

    lateinit var buttonStart : Button
    lateinit var buttonPause : Button
    lateinit var buttonStop : Button

    private lateinit var countDownTimer : CountDownTimer
    var startTime : Long = 0
    var timeLeftInMillis : Long = startTime
    var isTimerRunning : Boolean = false

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

        var buttonNumberAdd1 = findViewById<Button>(R.id.NumberAdd1)
        var buttonNumberAdd2 = findViewById<Button>(R.id.NumberAdd2)
        var buttonNumberAdd3 = findViewById<Button>(R.id.NumberAdd3)
        var buttonNumberAdd4 = findViewById<Button>(R.id.NumberAdd4)

        var buttonNumberSub1 = findViewById<Button>(R.id.NumberSub1)
        var buttonNumberSub2 = findViewById<Button>(R.id.NumberSub2)
        var buttonNumberSub3 = findViewById<Button>(R.id.NumberSub3)
        var buttonNumberSub4 = findViewById<Button>(R.id.NumberSub4)

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
        }
        buttonNumberAdd2.setOnClickListener {
            if (textViewNumber2.text == "9") {
                textViewNumber2.text = "0"
            } else {
                textViewNumber2.text = (parseInt(textViewNumber2.text as String) + 1).toString()
            }
        }
        buttonNumberAdd3.setOnClickListener {
            if (textViewNumber3.text == "5") {
                textViewNumber3.text = "0"
            } else {
                textViewNumber3.text = (parseInt(textViewNumber3.text as String) + 1).toString()
            }
        }
        buttonNumberAdd4.setOnClickListener {
            if (textViewNumber4.text == "9") {
                textViewNumber4.text = "0"
            } else {
                textViewNumber4.text = (parseInt(textViewNumber4.text as String) + 1).toString()
            }
        }

        buttonNumberSub1.setOnClickListener {
            if (textViewNumber1.text == "0") {
                textViewNumber1.text = "9"
            } else {
                textViewNumber1.text = (parseInt(textViewNumber1.text as String) - 1).toString()
            }
        }
        buttonNumberSub2.setOnClickListener {
            if (textViewNumber2.text == "0") {
                textViewNumber2.text = "9"
            } else {
                textViewNumber2.text = (parseInt(textViewNumber2.text as String) - 1).toString()
            }
        }
        buttonNumberSub3.setOnClickListener {
            if (textViewNumber3.text == "0") {
                textViewNumber3.text = "5"
            } else {
                textViewNumber3.text = (parseInt(textViewNumber3.text as String) - 1).toString()
            }
        }
        buttonNumberSub4.setOnClickListener {
            if (textViewNumber4.text == "0") {
                textViewNumber4.text = "9"
            } else {
                textViewNumber4.text = (parseInt(textViewNumber4.text as String) - 1).toString()
            }
        }

        updateCountDownText()
    }

    private fun startTimer() {
        timeLeftInMillis = countTimeToMillis()
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished : Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                isTimerRunning = false
                buttonStart.isEnabled = true
            }
        }.start()

        isTimerRunning = true
        buttonStart.isEnabled = false
    }

    private fun updateCountDownText() {
        var minutes : Int = ((timeLeftInMillis / 1000) / 60).toInt()
        var seconds : Int = ((timeLeftInMillis / 1000) % 60).toInt()

        textViewNumber1.text = (minutes / 10).toString()
        textViewNumber2.text = (minutes % 10).toString()
        textViewNumber3.text = (seconds / 10).toString()
        textViewNumber4.text = (seconds % 10).toString()
    }

    private fun countTimeToMillis(): Long {
        var  timeToMillis : Long = 0
        timeToMillis += (textViewNumber1.text as String).toLong() * 6000000
        timeToMillis += (textViewNumber2.text as String).toLong() * 600000
        timeToMillis += (textViewNumber3.text as String).toLong() * 10000
        timeToMillis += (textViewNumber4.text as String).toLong() * 1000
        return timeToMillis
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        isTimerRunning = false;
        buttonStart.isEnabled = true
    }

    private fun stopTimer() {
        countDownTimer.cancel()
        timeLeftInMillis = startTime
        updateCountDownText()
        isTimerRunning = false;
        buttonStart.isEnabled = true
    }
}