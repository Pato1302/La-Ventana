package com.example.laventana

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laventana.adapter.CustomAdapter
import com.example.laventana.databinding.ActivityMainBinding
import com.example.laventana.model.Lista
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity(), LocationListener {
    lateinit var binding: ActivityMainBinding
    lateinit var mapa: GoogleMap
    lateinit var locationManager: LocationManager
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*

        var mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager // Para obtener la ubicación del usuario

        mapFragment.getMapAsync { googleMap ->
            mapa = googleMap
            mapa.isMyLocationEnabled = true // Para mostrar el punto azul de la ubicación del usuario
            // Controles zoom
            mapa.uiSettings.isZoomControlsEnabled = true

            // Agregar marcadores
            for (i in Lista.lista) {
                mapa.addMarker(i.getMarker())
            }

            // Mover la cámara a la ubicación del usuario
            //mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(mapa.myLocation.latitude, mapa.myLocation.longitude), 15f))
        }


        binding.rvLista.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            val adapterBuilder = CustomAdapter(this@MainActivity, R.layout.cards, Lista.lista)
            adapter = adapterBuilder

            adapterBuilder.setOnItemClickListener(object : CustomAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(Lista.lista[position].latitud, Lista.lista[position].longitud), 15f))
                }

                override fun onIrButtonClick(position: Int) {
                    TODO("Not yet implemented")
                }

                override fun onVerButtonClick(position: Int) {
                    TODO("Not yet implemented")
                }

                override fun onInfoButtonClick(position: Int) {
                    TODO("Not yet implemented")
                }
            })
        }

        // Solicitar permiso de ubicación
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            // Si el permiso no está concedido, solicitarlo explicando su utilidad
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Muestra la explicación de manera asíncrona
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Se necesita permiso de ubicación")
                builder.setMessage("Se necesita permiso de ubicación para mostrar la ubicación del usuario en el mapa.")

                builder.setPositiveButton("OK") { dialog, id ->
                    ActivityCompat.requestPermissions(this@MainActivity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 10)
                }
                val dialog = builder.create()
                dialog.show()
            } else {
                // No se necesita explicación, solicitar el permiso
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 10)
                // 10 es una constante que se usa para saber que se pidio el permiso de ubicación
            }
        } else {
            // El permiso ya está concedido, obtener la ubicación
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }
    */

    }


    override fun onLocationChanged(location: Location) {
        Log.d("ubicacion", "Latitud: ${location.latitude} Longitud: ${location.longitude}")
    }




}