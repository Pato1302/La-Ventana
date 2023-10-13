package com.example.laventana.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.laventana.R

class Info : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_info, container, false)

        // Regresar a la pantalla anterior
        view.findViewById<View>(R.id.btnRegresar).setOnClickListener {
            requireActivity().onBackPressed()
        }

        // Traer los datos del lugar con los bundle
        val bundle = arguments
        val nombre = bundle?.getString("nombre")
        val descripcion = bundle?.getString("descripcion")
        val ubicacion = bundle?.getString("ubicacion")
        val telefono = bundle?.getString("telefono")
        val foto = bundle?.getInt("foto")

        // Mostrar los datos del lugar
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtNameInfo).text = nombre
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtDesInfo).text = descripcion
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtUbicacionInfo).text = ubicacion
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTelefonoInfo).text = telefono
        view.findViewById<androidx.appcompat.widget.AppCompatImageView>(R.id.imgImagenInfo).setImageResource(foto!!)

        return view
    }
}