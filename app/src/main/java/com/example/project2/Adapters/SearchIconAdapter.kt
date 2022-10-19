package com.example.project2.Adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project2.Icon
import com.example.project2.R

class SearchIconAdapter(val context: Context, val icons: List<Icon>): RecyclerView.Adapter<SearchIconAdapter.SearchIconViewHolder>() {
    class SearchIconViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var iconid1=itemView.findViewById<TextView>(R.id.iconid1)
        var iconImage1=itemView.findViewById<ImageView>(R.id.iconImage1)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position:Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchIconViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.searchicons_adapter_layout,parent,false)
        return SearchIconViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: SearchIconViewHolder, position: Int) {
        val icon=icons[position]
        holder.iconid1.text=icon.icon_id.toString()
        Glide.with(context).load(icon.raster_sizes[6].formats[0].preview_url).into(holder.iconImage1)
    }

    override fun getItemCount(): Int {
        return icons.size
    }
}