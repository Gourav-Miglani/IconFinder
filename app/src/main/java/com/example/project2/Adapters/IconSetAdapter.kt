package com.example.project2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.IconsetDataClass.Iconset
import com.example.project2.R

class IconSetAdapter(val context: Context, val iconsets: List<Iconset>): RecyclerView.Adapter<IconSetAdapter.IconsetViewHolder>() {
    class IconsetViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var iconsetName=itemView.findViewById<TextView>(R.id.iconsetName) //create layout
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconsetViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.iconset_adapter_layout,parent,false)
        return IconsetViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: IconsetViewHolder, position: Int) {
        val iconset=iconsets[position]
        holder.iconsetName.text=iconset.name
    }

    override fun getItemCount(): Int {
        return iconsets.size
    }
}