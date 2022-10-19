package com.example.project2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.Categoriesdataclasses.Category
import com.example.project2.R

class CategoryAdapter(val context: Context,val categories: List<Category>):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
  class CategoryViewHolder(itemView: View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
     var categoryName=itemView.findViewById<TextView>(R.id.categoryName)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.categories_layout,parent,false)
        return CategoryViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=categories[position]
        holder.categoryName.text=category.name
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}