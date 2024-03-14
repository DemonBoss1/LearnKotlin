package com.example.learnkotlin.ui.encoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.learnkotlin.databinding.FragmentEncodingBinding
import kotlin.math.pow

class EncodingFragment : Fragment() {

    private var _binding: FragmentEncodingBinding? = null
    private val binding get() = _binding!!
    val p = 59
    val q: Double = 61.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEncodingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val user1privateKey: Int = binding.user1privateKey
                .text
                .toString()
                .toInt()
            val user2privateKey: Int = binding.user2privateKey
                .text
                .toString()
                .toInt()

            val user1publicKey = q.pow(user1privateKey) % p
            binding.user1publicKey.text = user1publicKey.toString()
            val user2publicKey = q.pow(user2privateKey) % p
            binding.user2publicKey.text = user2publicKey.toString()

            val sharedSecret1 = user2publicKey.pow(user1privateKey) % p
            val sharedSecret2 = user1publicKey.pow(user2privateKey) % p
            if (sharedSecret1 == sharedSecret2)
                binding.sharedSecret.text = sharedSecret1.toString();
            else
                binding.sharedSecret.text = "ERROR"

            binding.apply {
                var string = messageUser1.text.toString()
                string = cipher(string, sharedSecret1.toInt())
                codingUser1.text = string
                string = cipher(string, -sharedSecret1.toInt())
                encodingUser1.text = string
                string = messageUser2.text.toString()
                string = cipher(string, sharedSecret1.toInt())
                codingUser2.text = string
                string = cipher(string, -sharedSecret1.toInt())
                encodingUser2.text = string
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cipher(text: String, shift: Int): String {
        var result = ""
        var char: Char
        for (i in text.indices) {
            char = text[i].toChar()
            char += shift
            result += char
        }
        return result
    }
}