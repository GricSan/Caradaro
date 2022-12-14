package com.gricsan.caradaro.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gricsan.caradaro.databinding.ActivityNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {

    private var _binding: ActivityNavHostBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNavHostBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}