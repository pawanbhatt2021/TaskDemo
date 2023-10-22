package com.example.taskdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskdemo.R
import com.example.taskdemo.databinding.ActivityMainBinding
import com.example.taskdemo.databinding.ActivitySplashScreenBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LoginScreen : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding?=null
    private val binding: ActivitySplashScreenBinding
        get() = _binding!!
    private val REQUEST_LOCATION = 1
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}