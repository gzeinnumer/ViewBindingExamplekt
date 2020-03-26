package com.gzeinnumer.viewbindingexamplekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gzeinnumer.viewbindingexamplekt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*
    android {
        viewBinding {
            enabled = true
        }
    }
    */

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tv.text = "Bisa set onclick disini"
    }
}
