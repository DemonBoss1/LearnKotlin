package com.example.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import com.example.learnkotlin.databinding.ActivityCalculatorBinding

class Calculator : AppCompatActivity() {
    private var string = ""
    private var text = ""
    private var numbers = listOf<Int>()
    private lateinit var bindingClass : ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }

    fun onClickNum(view: View) {
        val num = when (view.id){
            R.id.button0 -> "0"
            R.id.button1 -> "1"
            R.id.button2 -> "2"
            R.id.button3 -> "3"
            R.id.button4 -> "4"
            R.id.button5 -> "5"
            R.id.button6 -> "6"
            R.id.button7 -> "7"
            R.id.button8 -> "8"
            R.id.button9 -> "9"
            else -> ""
        }
        string += num
        bindingClass.input.text = string

    }

    fun onClickMathematicalOperation(view: View) {
        if (string == "") return
        when (view.id){
            R.id.button_plus -> {
                if(numbers.isEmpty()) {
                    numbers += string.toInt()
                } else {
                    val rez = numbers[0] + string.toInt()
                    numbers = listOf<Int>(rez)
                }
                text += "$string  \n +\n"
            }
            R.id.button_minus ->{
                if(numbers.isEmpty()) {
                    numbers += string.toInt()
                } else {
                    val rez = numbers[0] - string.toInt()
                    numbers = listOf<Int>(rez)
                }
                text += "$string  \n -\n"
            }
            R.id.button_multiplication ->{
                if(numbers.isEmpty()) {
                    numbers += string.toInt()
                } else {
                    val rez = numbers[0] * string.toInt()
                    numbers = listOf<Int>(rez)
                }
                text += "$string  \n *\n"
            }
            R.id.button_division ->{
                if(numbers.isEmpty()) {
                    numbers += string.toInt()
                } else {
                    val rez = numbers[0] / string.toInt()
                    numbers = listOf<Int>(rez)
                }
                text += "$string  \n /\n"
            }
            R.id.button_division_with_remainder ->{
                if(numbers.isEmpty()) {
                    numbers += string.toInt()
                } else {
                    val rez = numbers[0] % string.toInt()
                    numbers = listOf<Int>(rez)
                }
                text += "$string  \n %\n"
            }
            R.id.button_equally ->{}
        }
        string = ""
        bindingClass.historyOperation.text = text
        bindingClass.input.text = numbers[0].toString()
        bindingClass.scrollView.post(Runnable {
            bindingClass.scrollView.fullScroll(ScrollView.FOCUS_DOWN)
        })
    }
}
