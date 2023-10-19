package com.example.laventana.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Entity
data class Proyecto(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "idProyecto")
    var idProyecto: Int,
    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "categoria_proyecto")
    var categoriaProyecto: String,
    @ColumnInfo(name = "descripcion_proyecto")
    var descripcionProyecto: String,
    @ColumnInfo(name = "url_proyecto")
    var urlProyecto: String,
    @ColumnInfo(name = "logo_proyecto")
    var logoProyecto: String,
    @ColumnInfo(name = "imagen_proyecto")
    var imagenProyecto: String,
    @ColumnInfo(name = "tipo_proyecto")
    var tipoProyecto: String,
    @ColumnInfo(name = "estado")
    var estado: String,
    @ColumnInfo(name = "latitud")
    var latitud: Double,
    @ColumnInfo(name = "longitud")
    var longitud: Double,
    @ColumnInfo(name = "horario_apertura_proyecto")
    var horarioAperturaProyecto: String,
    @ColumnInfo(name = "horario_cierre_proyecto")
    var horarioCierreProyecto: String
){
    fun getMarker(): MarkerOptions {
        return MarkerOptions().position(
            LatLng(
                latitud,
                longitud
            )
        ).title(nombre)
    }
}