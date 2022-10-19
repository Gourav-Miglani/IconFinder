package com.example.project2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project2.Icon
import com.example.project2.R


class IconAdapter(val context: Context, val icons: List<Icon>): RecyclerView.Adapter<IconAdapter.IconViewHolder>() {
    class IconViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var iconid=itemView.findViewById<TextView>(R.id.iconid)
        var iconImage=itemView.findViewById<ImageView>(R.id.iconImage)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.icons_adapter_layout,parent,false)
        return IconViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val icon=icons[position]
        holder.iconid.text=icon.icon_id.toString()

        Glide.with(context).load(icon.raster_sizes[6].formats[0].preview_url).into(holder.iconImage)

    }

    override fun getItemCount(): Int {
        return icons.size
    }
}