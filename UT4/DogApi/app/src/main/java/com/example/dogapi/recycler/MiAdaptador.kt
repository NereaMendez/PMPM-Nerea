package com.example.dogapi.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogapi.R
import com.example.dogapi.model.DogRespuesta


//Infla my_row
class MiAdaptador( var misDatos: DogRespuesta): RecyclerView.Adapter<Vista>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vista {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_row, parent, false)

        return Vista(itemView)
    }

    override fun onBindViewHolder(holder: Vista, position: Int) {

        //obtenemos la url de la imagen de la lista message
        val url = misDatos.message?.get(position) ?: ""

        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.imageView1)

    }
    override fun getItemCount(): Int = misDatos.message?.size ?: 0
    fun actualizarDatos(nuevosDatos: DogRespuesta) {
        this.misDatos = nuevosDatos
        notifyDataSetChanged()
    }




    }


