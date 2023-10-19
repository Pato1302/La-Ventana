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
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.laventana.BottomNav
import com.example.laventana.R
import com.example.laventana.adapter.CustomAdapter
import com.example.laventana.model.Lista
import com.example.laventana.model.Proyecto
import com.example.laventana.ui.info.Info
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.example.laventana.ui.map.Map
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.tec.a2doexamen.utility.AppDatabase
import org.json.JSONObject


class Cards : Fragment() {
    lateinit var rvLista: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.fragment_cards, container, false)

        // Inicializar base de datos
        val db = AppDatabase.getInstance(this.requireContext())
        val queue = Volley.newRequestQueue(this.requireContext())
        val lista = mutableListOf<Proyecto>()

        rvLista = view.findViewById(R.id.rvLista)

        val url = "https://1mfu6t7gmh.execute-api.us-east-1.amazonaws.com/proyecto"

        val listener = Response.Listener<JSONObject> { response ->
            val item = response.getJSONArray("proyectos")
            for (j in 0 until item.length()) {
                val proyecto = Proyecto(
                    item.getJSONObject(j).getInt("id_proyecto"),
                    item.getJSONObject(j).getString("nombre_proyecto"),
                    item.getJSONObject(j).getString("categoria_proyecto"),
                    item.getJSONObject(j).getString("descripcion_proyecto"),
                    item.getJSONObject(j).getString("url_proyecto"),
                    item.getJSONObject(j).getString("logo_proyecto"),
                    item.getJSONObject(j).getString("imagen_proyecto"),
                    item.getJSONObject(j).getString("tipo_proyecto"),
                    item.getJSONObject(j).getString("estado"),
                    item.getJSONObject(j).getDouble("latitud"),
                    item.getJSONObject(j).getDouble("longitud"),
                    item.getJSONObject(j).getString("horario_apertura_proyecto"),
                    item.getJSONObject(j).getString("horario_cierre_proyecto")
                )
                lista.add(proyecto)
            }
            Combinacion(db, lista)
        }

        val error = Response.ErrorListener { error ->
            //Log.e("RESTLIBS", error.message!!)
        }

        val jsonArrayRequest = JsonObjectRequest(com.android.volley.Request.Method.GET, url, null, listener, error)
        queue.add(jsonArrayRequest)

        return view
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun Combinacion(db: AppDatabase, lista: MutableList<Proyecto>) {
        GlobalScope.launch(Dispatchers.IO) {
            db.proyectoDao().borrarProyectos()
            for (i in lista) {
                db.proyectoDao().registrarProyecto(i) // Registrar en la base de datos local
            }
            val list = db.proyectoDao().obtenerProyectos() // Obtener la lista de la base de datos local

            withContext(Dispatchers.Main) {

                rvLista.apply {
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    setHasFixedSize(true)
                    val adapterBuilder = CustomAdapter(requireActivity(), R.layout.cards, list)
                    adapter = adapterBuilder


                    // Agregar listener al adapter con los botonos de cada elemento
                    adapterBuilder.setOnItemClickListener(object :
                        CustomAdapter.OnItemClickListener {
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
                                list[position].latitud,
                                list[position].longitud
                            )
                        }


                        override fun onInfoButtonClick(position: Int) {
                            // Abrir el fragmento de información
                            val fragment = Info()
                            val transaction =
                                requireActivity().supportFragmentManager.beginTransaction()
                            transaction.replace(R.id.contenedor, fragment)
                            transaction.addToBackStack(null)
                            transaction.commit()

                            // Para pasar los datos del lugar al fragmento de información
                            val bundle = Bundle()
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
                            fragment.arguments = bundle
                        }
                    })
                }
            }
        }
    }


}