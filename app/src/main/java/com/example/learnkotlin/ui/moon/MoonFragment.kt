package com.example.learnkotlin.ui.moon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.learnkotlin.databinding.FragmentMoonBinding

class MoonFragment : Fragment() {

private var _binding: FragmentMoonBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMoonBinding.inflate(inflater, container, false)
    return binding.root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkButton.setOnClickListener{
            CheckNumber();
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun CheckNumber() {
        val cardNumber = binding.bankCardNumber.text
        var number = 0
        if(cardNumber.length == 16) {
            for(i in 0..15){
                if(i%2==0) {
                    var numberInt = cardNumber[i].digitToInt()
                    numberInt *= 2
                    val numberString = numberInt.toString()
                    numberInt = 0;
                    for (j in numberString.indices)
                        numberInt += numberString[j].digitToInt()
                    number += numberInt
                }else{
                    number += cardNumber[i].digitToInt()
                }
            }
            if(number%10 == 0)
                binding.message.text = "Номер карты введен верно"
            else
                binding.message.text = "Номер карты введен неверно"
        }
        else
            binding.message.text = "Error"
    }
}