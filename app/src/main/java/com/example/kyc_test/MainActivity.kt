package com.example.kyc_test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.kyc_test.databinding.ActivityMainBinding
import com.example.kyc_test.fragments.CameraFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        val sharedPref = this.getSharedPreferences("Confirm", Context.MODE_PRIVATE)
//        sharedPref.edit().clear().apply()
//    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPref = this.getSharedPreferences("SelfieSharedPref",Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()
        val sharedPrefs = this.getSharedPreferences("TakePhotoSharedPref",Context.MODE_PRIVATE)
        sharedPrefs.edit().clear().apply()
    }
}