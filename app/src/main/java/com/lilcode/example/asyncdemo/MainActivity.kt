package com.lilcode.example.asyncdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lilcode.example.asyncdemo.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buttonClick(view: View) {
        var i = 0
        while (i <= 20) {
            try {
                Thread.sleep(1000)
                i++
            } catch (e: Exception){
            }
        }
        binding.myTextView.text = "Button Pressed"
    }
}