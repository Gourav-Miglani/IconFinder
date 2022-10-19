package com.example.project2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project2.Categoriesdataclasses.RasterSize
import com.example.project2.IconsetDataClass.Iconset
import com.example.project2.R
import kotlinx.android.synthetic.main.downloadfragment_adapterlayout.view.*

class DownloadFragmentAdapter(val context: Context, val rastersizes: List<RasterSize>): RecyclerView.Adapter<DownloadFragmentAdapter.DownloadFragmentViewHolder>() {
    class DownloadFragmentViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        var downloadButton=itemView.findViewById<Button>(R.id.downloadButton)
        init {
            itemView.downloadButton.setOnClickListener {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadFragmentViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.downloadfragment_adapterlayout,parent,false)
        return DownloadFragmentViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: DownloadFragmentViewHolder, position: Int) {
        val rastersize=rastersizes[position]
        holder.downloadButton.text="Download icon of size: "+rastersize.size.toString()
    }

    override fun getItemCount(): Int {
        return rastersizes.size
    }
}