package com.example.laventana.model

import com.google.android.gms.maps.model.MarkerOptions

data class Elemento(
    val nombre: String,
    val ubicacion: String,
    val foto: Int,
    val telefono: String,
    val latitud: Double,
    val longitud: Double,
    val descripcion: String
) {
    fun getMarker(): MarkerOptions {
        return MarkerOptions().position(
            com.google.android.gms.maps.model.LatLng(
                latitud,
                longitud
            )
        ).title(nombre)
    }
}
