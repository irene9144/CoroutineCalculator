package com.example.examplesquarecalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var inputNumber: EditText
    private lateinit var calculatorButton: Button
    private lateinit var resultText: TextView
    private val calculator = AdvancedCalculator()
    //private val calculator = SquareCalculator()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputNumber = findViewById(R.id.inputNumber)
        calculatorButton = findViewById(R.id.calculateButton)
        resultText = findViewById(R.id.resultText)

        calculatorButton.setOnClickListener {
            val number = inputNumber.text.toString().toIntOrNull()
            if(number != null) {
                lifecycleScope.launch {
                    val result = withContext(Dispatchers.Default){
                        calculator.performCalculation(number, ::square)
                        calculator.performCalculation(number){it * it}
                    }

                    resultText.text = "결과: $result"
                }
            } else {
                resultText.text = "유효한 숫자를 입력하세요."
            }
        }
    }
    fun square(number: Int): Int {
        return number * number
    }
}