package com.example.laventana.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laventana.BottomNav
import com.example.laventana.R
import com.example.laventana.adapter.CustomAdapter
import com.example.laventana.model.Lista
import com.example.laventana.ui.info.Info
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.example.laventana.ui.map.Map


class Cards : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_cards, container, false)

        val rvLista = view.findViewById<RecyclerView>(R.id.rvLista)

        rvLista.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            val adapterBuilder = CustomAdapter(requireActivity(), R.layout.cards, Lista.lista)
            adapter = adapterBuilder


            // Agregar listener al adapter con los botonos de cada elemento
            adapterBuilder.setOnItemClickListener(object : CustomAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    //Toast.makeText(requireActivity(), "Elemento $position", Toast.LENGTH_SHORT).show()
                }

                override fun onIrButtonClick(position: Int) {
                    // Cambiar el bottom navigation
                }

                override fun onVerButtonClick(position: Int) {
                    // Cambiar el bottom navigation
                    val activity = requireActivity() as BottomNav
                    activity.ChangeItem(
                        "mapa",
                        Lista.lista[position].latitud,
                        Lista.lista[position].longitud
                    )
                }


                override fun onInfoButtonClick(position: Int) {
                    Toast.makeText(
                        requireActivity(),
                        "Info ${Lista.lista[position].nombre}",
                        Toast.LENGTH_SHORT
                    ).show()


                    // Abrir el fragmento de información
                    val fragment = Info()
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.contenedor, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()

                    // Para pasar los datos del lugar al fragmento de información
                    val bundle = Bundle()
                    bundle.putString("nombre", Lista.lista[position].nombre)
                    bundle.putString("descripcion", Lista.lista[position].descripcion)
                    bundle.putString("ubicacion", Lista.lista[position].ubicacion)
                    bundle.putString("telefono", Lista.lista[position].telefono)
                    bundle.putInt("foto", Lista.lista[position].foto)
                    fragment.arguments = bundle
                }
            })
        }
        return view
    }
}