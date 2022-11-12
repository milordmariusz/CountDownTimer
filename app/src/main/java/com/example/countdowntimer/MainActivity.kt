package com.example.countdowntimer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private lateinit var textViewNumber1: TextView
    private lateinit var textViewNumber2: TextView
    private lateinit var textViewNumber3: TextView
    private lateinit var textViewNumber4: TextView

    private lateinit var buttonStart: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonStop: Button

    lateinit var buttonNumberAdd1: Button
    lateinit var buttonNumberAdd2: Button
    lateinit var buttonNumberAdd3: Button
    lateinit var buttonNumberAdd4: Button

    lateinit var buttonNumberSub1: Button
    lateinit var buttonNumberSub2: Button
    lateinit var buttonNumberSub3: Button
    lateinit var buttonNumberSub4: Button

    var listOfButtons = listOf<Button>()


    private lateinit var countDownTimer: CountDownTimer
    private var startTime: Long = 0
    var timeLeftInMillis: Long = startTime

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

        buttonNumberAdd1 = findViewById(R.id.NumberAdd1)
        buttonNumberAdd2 = findViewById(R.id.NumberAdd2)
        buttonNumberAdd3 = findViewById(R.id.NumberAdd3)
        buttonNumberAdd4 = findViewById(R.id.NumberAdd4)

        buttonNumberSub1 = findViewById(R.id.NumberSub1)
        buttonNumberSub2 = findViewById(R.id.NumberSub2)
        buttonNumberSub3 = findViewById(R.id.NumberSub3)
        buttonNumberSub4 = findViewById(R.id.NumberSub4)

        listOfButtons = listOf(
            buttonNumberAdd1,
            buttonNumberAdd2,
            buttonNumberAdd3,
            buttonNumberAdd4,
            buttonNumberSub1,
            buttonNumberSub2,
            buttonNumberSub3,
            buttonNumberSub4
        )

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
        buttonStart.isEnabled = !isTimeEqualZero()
        buttonPause.isEnabled = false
    }

    private fun isTimeEqualZero(): Boolean {
        return textViewNumber1.text == "0" &&
                textViewNumber2.text == "0" &&
                textViewNumber3.text == "0" &&
                textViewNumber4.text == "0"
    }

    private fun startTimer() {
        disableTimeManipulation()
        timeLeftInMillis = countTimeToMillis()
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                enableTimeManipulation()
                updateButtons()
            }
        }.start()

        buttonStart.isEnabled = false
    }

    private fun enableTimeManipulation() {
        for (button in listOfButtons){
            button.isEnabled = true
        }
        buttonPause.isEnabled = false

    }

    private fun disableTimeManipulation() {
        for (button in listOfButtons){
            button.isEnabled = false
        }
        buttonPause.isEnabled = true
    }

    private fun updateCountDownText() {
        val minutes: Int = ((timeLeftInMillis / 1000) / 60).toInt()
        val seconds: Int = ((timeLeftInMillis / 1000) % 60).toInt()

        textViewNumber1.text = (minutes / 10).toString()
        textViewNumber2.text = (minutes % 10).toString()
        textViewNumber3.text = (seconds / 10).toString()
        textViewNumber4.text = (seconds % 10).toString()
    }

    private fun countTimeToMillis(): Long {
        var timeToMillis: Long = 0
        timeToMillis += (textViewNumber1.text as String).toLong() * 600000
        timeToMillis += (textViewNumber2.text as String).toLong() * 60000
        timeToMillis += (textViewNumber3.text as String).toLong() * 10000
        timeToMillis += (textViewNumber4.text as String).toLong() * 1000
        return timeToMillis
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        enableTimeManipulation()
        updateButtons()
    }

    private fun stopTimer() {
        countDownTimer.cancel()
        enableTimeManipulation()
        timeLeftInMillis = startTime
        updateCountDownText()
        updateButtons()
    }
}