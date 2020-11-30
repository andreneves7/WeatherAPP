package com.example.weather

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

class ListActivity : AppCompatActivity() {

    lateinit var listView: ListView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var locationUpdateState = false

    lateinit var localizacao: Button

    var latitude = 0.0000000
    var longitude = 0.0000000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listView = findViewById(R.id.listView)
        var list = mutableListOf<Model>()

        list.add(Model("London", R.drawable.london))
        list.add(Model("Lisboa", R.drawable.portugal))
        list.add(Model("Madrid", R.drawable.madrid))
        list.add(Model("Paris", R.drawable.paris))
        list.add(Model("Berlim", R.drawable.berlim))
        list.add(Model("Copenhaga", R.drawable.copenhaga))
        list.add(Model("Roma", R.drawable.roma))
        list.add(Model("Dublin", R.drawable.dublin))
        list.add(Model("Praga", R.drawable.praga))
        list.add(Model("Viena", R.drawable.viena))

        listView.adapter = ListAdapter(this, R.layout.row, list)

        listView.setOnItemClickListener { parent, view, position, id ->

            when (position){
                0 -> {
                    val nome = "London"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                1->{
                    val nome = "Lisboa"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                2->{
                    val nome = "Madrid"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                3->{
                    val nome = "Paris"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                4->{
                    val nome = "Berlim"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                5->{
                    val nome = "Copenhaga"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                6->{
                    val nome = "Roma"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                7->{
                    val nome = "Dublin"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                8->{
                    val nome = "Praga"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }

                9->{
                    val nome = "Viena"
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, nome)
                    }
                    startActivity(intent)
                }
            }

        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)




        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation

            }
        }


        createLocationRequest()

        localizacao = findViewById(R.id.bLocal)

        localizacao.setOnClickListener {

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->

                    if (location != null) {
                        lastLocation = location
                        Log.d("Mapa", "$lastLocation}")
                        Log.d("Mapa", "latitude ${location.latitude}}")
                        Log.d("Mapa", "longitude ${location.longitude}}")
                        latitude = location.latitude.toDouble()
                        longitude = location.longitude.toDouble()
                        val marca = 1
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("marca", marca.toString())
                        intent.putExtra("latitude", latitude.toString())
                        intent.putExtra("longitude", longitude.toString())

                        startActivity(intent)
                    }
                }





        }




        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {


            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                ListActivity.Location_Result_Code
            )

            return
        }





    }


    companion object {
        private const val Location_Result_Code = 111
        private const val REQUEST_CHECK_SETTINGS = 2


    }

    private fun startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), Location_Result_Code
            )

            return
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            Location_Result_Code -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {

                } else {

                }
                return
            }


        }
    }

    private fun createLocationRequest() {

        locationRequest = LocationRequest()

        locationRequest.interval = 10000

        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())


        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->

            if (e is ResolvableApiException) {

                try {

                    e.startResolutionForResult(
                        this@ListActivity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {

                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }


    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }


}
