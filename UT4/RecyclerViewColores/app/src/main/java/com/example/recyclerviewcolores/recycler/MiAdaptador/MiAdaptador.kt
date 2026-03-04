package com.example.recyclerviewcolores.recycler.MiAdaptador

import android.graphics.Color as AndroidColor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolores.R
import com.example.recyclerviewcolores.model.Datos

class MiAdaptador(private var misDatos: Datos) : RecyclerView.Adapter<Vista>() {

    private var posicionSeleccionada = RecyclerView.NO_POSITION

    //Infla el diseño de la fila ,my_row, para el RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vista {

        val viewFila = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)
        return Vista(viewFila)
    }

    override fun onBindViewHolder(holder: Vista, position: Int) {

        val colorActual = misDatos.colores[position]
        val codigoHex = colorActual.CodHexadecimal

        /*holder.txtTitulo.text = colorActual.nombre
        holder.txtHexadecimal.text = colorActual.CodHexadecimal*/

        with(holder) {
            txtTitulo.text = colorActual.nombre
            txtHexadecimal.text = codigoHex
        }

        //Lógica de selección y colores
        if (position == posicionSeleccionada) {
            holder.fila.setBackgroundColor(AndroidColor.WHITE)
            holder.txtTitulo.setTextColor(AndroidColor.parseColor(colorActual.CodHexadecimal))
            holder.txtHexadecimal.setTextColor(AndroidColor.parseColor(colorActual.CodHexadecimal))
        } else {
            holder.fila.setBackgroundColor(AndroidColor.parseColor(colorActual.CodHexadecimal))
            holder.txtTitulo.setTextColor(AndroidColor.BLACK)
            holder.txtHexadecimal.setTextColor(AndroidColor.BLACK)
        }

        //Listener para detectar el toque en la fila y refrescar el estado
        holder.fila.setOnClickListener {

            val anterior = posicionSeleccionada
            posicionSeleccionada = holder.adapterPosition

            //Refrescar solo las dos celdas afectadas....
            notifyItemChanged(anterior)
            notifyItemChanged(posicionSeleccionada)
        }
    }

    override fun getItemCount(): Int = misDatos.colores.size
}