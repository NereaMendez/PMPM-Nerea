package com.example.dogapi.recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapi.R

class Vista(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView1: ImageView = itemView.findViewById(R.id.imageView1)
}