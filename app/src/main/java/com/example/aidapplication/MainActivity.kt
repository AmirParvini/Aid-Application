package com.example.aidapplication

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.app.ActivityCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isStoragePermissionGranted()

        //findViewById  -   -   -   -   -   -   -   -   -   -   -   -   -   -   -
        mapView = findViewById(R.id.mapView)
        //findViewById  -   -   -   -   -   -   -   -   -   -   -   -   -   -   -



        //Show OSM Map  -   -   -   -   -   -   -   -   -   -   -   -   -   -   -
        Configuration.getInstance().load(applicationContext, PreferenceManager.getDefaultSharedPreferences(
            applicationContext
        ))
        mapView.setMultiTouchControls(true)
        val mapController = mapView.controller
        mapController.setZoom(16.0)
        val startPoint = GeoPoint(35.76, 51.47)
        mapController.setCenter(startPoint)
        //Show OSM Map  -   -   -   -   -   -   -   -   -   -   -   -   -   -   -
    }


    private fun isStoragePermissionGranted(): Boolean {

        if (checkSelfPermission(android.Manifest.permission.INTERNET)
            == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

            Log.v(TAG, "Permission is granted")
            return true
        }
        else {
            Log.v(TAG, "Permission is revoked")
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    android.Manifest.permission.INTERNET,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1)
            return false
        }

    }
}