package com.example.recyclerviewcolores.recycler.MiAdaptador

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewcolores.R

class Vista(miFila : View) : RecyclerView.ViewHolder(miFila) {
    var txtTitulo = miFila.findViewById<TextView>(R.id.txtTitulo)
    var txtHexadecimal = miFila.findViewById<TextView>(R.id.txtColorHex)
    var fila = miFila.findViewById<LinearLayout>(R.id.fila)
}