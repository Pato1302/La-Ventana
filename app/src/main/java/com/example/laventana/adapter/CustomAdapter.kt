package com.example.laventana.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laventana.R
import com.example.laventana.model.Proyecto
import com.bumptech.glide.Glide

class CustomAdapter(
    val context: Context,
    val layout: Int,
    val dataSource: List<Proyecto>
) : RecyclerView.Adapter<CustomAdapter.ElementoViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onIrButtonClick(position: Int)
        fun onVerButtonClick(position: Int)
        fun onInfoButtonClick(position: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    class ElementoViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        layout: Int,
        itemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(inflater.inflate(layout, parent, false)) {
        var nombre: TextView? = null
        var ubicacion: TextView? = null
        var logo: ImageView? = null
        var categoria: TextView? = null
        var buttonIr: Button? = null
        var buttonVer: Button? = null
        var buttonInfo: Button? = null

        init {
            nombre = itemView.findViewById(R.id.txtName) as TextView
            ubicacion = itemView.findViewById(R.id.txtUbicacion) as TextView
            logo = itemView.findViewById(R.id.imgImagen) as ImageView
            categoria = itemView.findViewById(R.id.txtCategoria) as TextView
            buttonIr = itemView.findViewById(R.id.btnIr) as Button
            buttonVer = itemView.findViewById(R.id.btnVer) as Button
            buttonInfo = itemView.findViewById(R.id.btnInfo) as Button

            // Establecer ClickListeners para los botones
            buttonIr?.setOnClickListener {
                itemClickListener.onIrButtonClick(adapterPosition)
            }
            buttonVer?.setOnClickListener {
                itemClickListener.onVerButtonClick(adapterPosition)
            }
            buttonInfo?.setOnClickListener {
                itemClickListener.onInfoButtonClick(adapterPosition)
            }
        }

        fun bind(elemento: Proyecto) {
            nombre!!.text = elemento.nombre
            ubicacion!!.text = elemento.estado
            categoria!!.text = elemento.categoriaProyecto
            Glide.with(itemView.context).load(elemento.logoProyecto).into(logo!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementoViewHolder { // Es el que crea los elementos
        val inflater = LayoutInflater.from(parent.context)
        return ElementoViewHolder(inflater, parent, layout, itemClickListener!!)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: ElementoViewHolder, position: Int) { // Se ejecuta por cada elemento de la lista
        val elemento: Proyecto = dataSource[position]
        holder.bind(elemento)

        holder.itemView.setOnClickListener { // Establecer el listener para el elemento
            itemClickListener?.onItemClick(position)
        }

        holder.buttonIr?.setOnClickListener { // Establecer el listener para el botón
            itemClickListener?.onIrButtonClick(position)
        }

        holder.buttonVer?.setOnClickListener { // Establecer el listener para el botón
            itemClickListener?.onVerButtonClick(position)
        }

        holder.buttonInfo?.setOnClickListener { // Establecer el listener para el botón
            itemClickListener?.onInfoButtonClick(position)
        }
    }

}