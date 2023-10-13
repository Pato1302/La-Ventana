package com.example.laventana.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.laventana.R
import com.example.laventana.model.Lista
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class Map: Fragment(), LocationListener {
    lateinit var mapa: GoogleMap
    lateinit var locationManager: LocationManager
    var cont = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_map, container, false)

        var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager // Para obtener la ubicación del usuario


        var latitud = 0.0
        var longitud = 0.0

        // Obtener los datos del lugar con shared preferences
        val sharedPreferences = requireActivity().getSharedPreferences("location", Context.MODE_PRIVATE)
        // Si no hay datos, poner la ubicación de mexico
        if (sharedPreferences.getString("latitud", "Latitud") != "Latitud") {
            latitud = sharedPreferences.getString("latitud", "Latitud")?.toDouble() ?: 0.0
            longitud = sharedPreferences.getString("longitud", "Longitud")?.toDouble() ?: 0.0
        }

        mapFragment.getMapAsync { googleMap ->
            mapa = googleMap
            mapa.isMyLocationEnabled = true // Para mostrar el punto azul de la ubicación del usuario
            // Controles zoom
            mapa.uiSettings.isZoomControlsEnabled = true // Para mostrar los controles de zoom

            // Agregar marcadores
            for (i in Lista.lista) {
                mapa.addMarker(i.getMarker())
            }

            // Mover la camara a mexico
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(23.634501, -102.552784), 5f))

            // Mover la cámara a la ubicación del lugar
            if (latitud != 0.0 && longitud != 0.0) {
                mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitud, longitud), 15f))
            }
        }
        // Solicitar permiso de ubicación
        if (ActivityCompat.checkSelfPermission(
                this.requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            // Si el permiso no está concedido, solicitarlo explicando su utilidad
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Muestra la explicación de manera asíncrona
                val builder = AlertDialog.Builder(this.requireActivity())
                builder.setTitle("Se necesita permiso de ubicación")
                builder.setMessage("Se necesita permiso de ubicación para mostrar la ubicación del usuario en el mapa.")

                builder.setPositiveButton("OK") { dialog, id ->
                    ActivityCompat.requestPermissions(this.requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 10)
                }
                val dialog = builder.create()
                dialog.show()
            } else {
                // No se necesita explicación, solicitar el permiso
                ActivityCompat.requestPermissions(
                    this.requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 10)
                // 10 es una constante que se usa para saber que se pidio el permiso de ubicación
            }
        } else {
            // El permiso ya está concedido, obtener la ubicación
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }

        return view
    }

    override fun onLocationChanged(location: Location) {
        //Log.d("Map", "Latitud: ${location.latitude} Longitud: ${location.longitude}")

    }

}