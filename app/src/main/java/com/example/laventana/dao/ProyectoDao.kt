package com.example.laventana.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.laventana.model.Proyecto

@Dao
interface ProyectoDao {
    @Query("SELECT * FROM Proyecto")
    fun obtenerProyectos(): List<Proyecto>

    @Query("SELECT * FROM Proyecto WHERE idProyecto = :idProyecto") // :idProyecto es un placeholder
    fun obtenerProyecto(idProyecto: Int): Proyecto


    @Query("SELECT * FROM Proyecto WHERE categoria_proyecto = :categoriaProyecto") // :categoriaProyecto es un placeholder
    fun obtenerProyectosPorCategoria(categoriaProyecto: String): List<Proyecto>

    @Query("DELETE FROM Proyecto")
    fun borrarProyectos() // Borrar base de datos

    // Ingresar valores a la base de datos local desde la base de datos remota sin repetir valores
    @Insert
    fun registrarProyecto(proyecto: Proyecto)
}