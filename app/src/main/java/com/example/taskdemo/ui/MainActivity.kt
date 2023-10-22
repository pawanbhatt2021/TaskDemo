package com.example.taskdemo.ui

import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.taskdemo.R
import com.example.taskdemo.databinding.ActivityMainBinding
import com.example.taskdemo.model.Form
import com.example.taskdemo.viewModel.DataViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mGoogleMap: GoogleMap
    private lateinit var mMapView: MapView
    private lateinit var marker: Marker
    private val markerList: ArrayList<LatLng> = ArrayList()
    private val viewmodel: DataViewModel by viewModels()
    private val locationPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            when {
                it.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                }

                it.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mMapView = binding.mapView
        mMapView.onCreate(savedInstanceState)
        mMapView.getMapAsync(this)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        checkLocationPermission()


        binding.point.setOnClickListener{
            binding.point.isEnabled=false
            mGoogleMap.uiSettings.isMyLocationButtonEnabled = true
            mGoogleMap.uiSettings.setAllGesturesEnabled(false)
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissions.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else {
            getUserLocation()
        }
    }

    private fun getUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it != null) {
                val lat = it.latitude
                val long = it.longitude
                val userLocation = LatLng(lat, long)
                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(userLocation, 15f)
                val marker = MarkerOptions().title("My Location").position(userLocation)
                marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.user_map_ic))
                mGoogleMap.animateCamera(cameraUpdate)
                mGoogleMap.addMarker(marker)
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap

        mGoogleMap.setOnMapClickListener {
            if (binding.point.isEnabled){
                mGoogleMap.uiSettings.isMyLocationButtonEnabled = true
                mGoogleMap.uiSettings.setAllGesturesEnabled(false)
                Toast.makeText(this, "Please enable point option", Toast.LENGTH_SHORT).show()
            }
            else{
                mGoogleMap.uiSettings.isMyLocationButtonEnabled = true
                mGoogleMap.setOnMapClickListener { latLng ->
                    // Add a marker to the map
                    val marker = mGoogleMap.addMarker(
                        MarkerOptions()
                            .position(latLng)
                    )
                    if (marker != null) {
                        markerList.add(LatLng(marker.position.latitude, marker.position.longitude))
                        val dialog = Dialog(this, android.R.style.Theme_DeviceDefault_Light)
                        dialog.setContentView(R.layout.form)

                        val exit:MaterialButton=dialog.findViewById(R.id.exit)
                        val submit:MaterialButton=dialog.findViewById(R.id.submit)
                        submit.setOnClickListener{
                            insertData(dialog)

                        }

                        exit.setOnClickListener{
                            binding.point.isEnabled=true
                            mGoogleMap.uiSettings.setAllGesturesEnabled(false)
                            dialog.dismiss()
                        }

                        dialog.show()
                        dialog.setCancelable(false)

                        Log.d("ArrayList", markerList.toString())
                    }
                }
                mGoogleMap.setOnMarkerClickListener { marker ->
                    markerList.remove(LatLng(marker.position.latitude, marker.position.longitude))
                    marker.remove()
                    true
                }
            }
        }
    }

    private fun insertData(dialog: Dialog) {
        val survey=dialog.findViewById<EditText>(R.id.survey).text.toString()
        val village=dialog.findViewById<EditText>(R.id.village).text.toString()
        val district=dialog.findViewById<EditText>(R.id.district).text.toString()
        val field4=dialog.findViewById<EditText>(R.id.field4).text.toString()
        val field5=dialog.findViewById<EditText>(R.id.field5).text.toString()
        val field6=dialog.findViewById<EditText>(R.id.field6).text.toString()
        val field7=dialog.findViewById<EditText>(R.id.field7).text.toString()
        val field8=dialog.findViewById<EditText>(R.id.field8).text.toString()
        val field9=dialog.findViewById<EditText>(R.id.field9).text.toString()
        val field10=dialog.findViewById<EditText>(R.id.field10).text.toString()
        lifecycleScope.launch(Dispatchers.IO) {

            val data = Form(null,
                survey,
                village,
                district,
                field4,
                field5,
                field6,
                field7,
                field8,
                field9,
                field10)
            viewmodel.insertNote(data)
        }
    }


    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mMapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }
}