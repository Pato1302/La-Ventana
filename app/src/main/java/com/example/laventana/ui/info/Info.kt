package com.example.laventana.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.laventana.R

class Info : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_info, container, false)

        // Regresar a la pantalla anterior
        view.findViewById<View>(R.id.btnRegresar).setOnClickListener {
            requireActivity().onBackPressed()
        }

        /*
            bundle.putString("nombre", list[position].nombre)
            bundle.putString("categoria", list[position].categoriaProyecto)
            bundle.putString("descripcion", list[position].descripcionProyecto)
            bundle.putString("url", list[position].urlProyecto)
            bundle.putString("logo", list[position].logoProyecto)
            bundle.putString("imagen", list[position].imagenProyecto)
            bundle.putString("tipo", list[position].tipoProyecto)
            bundle.putString("estado", list[position].estado)
            bundle.putString("horario_apertura", list[position].horarioAperturaProyecto)
            bundle.putString("horario_cierre", list[position].horarioCierreProyecto)
         */
        // Traer los datos del lugar con los bundle
        val bundle = arguments
        val nombre = bundle?.getString("nombre")
        val descripcion = bundle?.getString("descripcion")
        val url = bundle?.getString("url")
        val logo = bundle?.getString("logo")
        val imagen = bundle?.getString("imagen")
        val tipo = bundle?.getString("tipo")
        val estado = bundle?.getString("estado")
        val horarioApertura = bundle?.getString("horario_apertura")
        val horarioCierre = bundle?.getString("horario_cierre")

        // Mostrar el logo del lugar usando glide
        Glide.with(this).load(logo).into(view.findViewById<ImageView>(R.id.imgLogoInfo))

        // Mostrar la imagen del lugar usando glide
        Glide.with(this).load(imagen).into(view.findViewById<ImageView>(R.id.imgImagenInfo))


        // Mostrar los datos del lugar
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtNameInfo).text = nombre
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtDesInfo).text = descripcion
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtUrlInfo).text = url
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtTipoInfo).text = tipo
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtEstadoInfo).text = estado
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtApInfo).text = horarioApertura
        view.findViewById<androidx.appcompat.widget.AppCompatTextView>(R.id.txtCierrInfo).text = horarioCierre

        return view
    }
}