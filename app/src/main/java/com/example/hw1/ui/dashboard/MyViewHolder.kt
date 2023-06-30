package com.example.hw1.ui.dashboard

import android.media.Image
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.R

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.gallary_img)
    private val textView: TextView = itemView.findViewById(R.id.gallary_text)
}