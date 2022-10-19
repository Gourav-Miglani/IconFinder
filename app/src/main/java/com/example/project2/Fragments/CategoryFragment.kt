package com.example.project2.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.Adapters.CategoryAdapter
import com.example.project2.Categoriesdataclasses.Categories
import com.example.project2.IconService
import com.example.project2.MainActivity
import com.example.project2.R
import kotlinx.android.synthetic.main.fragment_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment: Fragment(R.layout.fragment_category) {
    lateinit var adapter: CategoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_categoryFragment_to_searchIcon_Fragment
            )
        }
        getCategories()
    }
    private fun getCategories() {
        val categories = IconService.IconInstance.getCategories()

        categories.enqueue(object: Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                val categories = response.body()
                if (categories != null) {

                    adapter= CategoryAdapter(activity as MainActivity,categories.categories)
                    CategoryList.adapter=adapter

                    CategoryList.layoutManager= LinearLayoutManager(activity)
                    adapter.setOnItemClickListener(object: CategoryAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val bundle = Bundle()
                            val str=categories.categories[position].identifier
                            bundle.putString("category",str)

                            findNavController().navigate(
                                R.id.action_categoryFragment_to_iconsetFragment,
                                bundle)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.d("error", "Error in fetching icons", t)
            }

        })
    }

}