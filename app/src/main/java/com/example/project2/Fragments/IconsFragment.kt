package com.example.project2.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.Adapters.IconAdapter
import com.example.project2.IconService
import com.example.project2.MainActivity
import com.example.project2.R
import com.example.project2.Categoriesdataclasses.icons
import kotlinx.android.synthetic.main.icons_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IconsFragment: Fragment(R.layout.icons_fragment)  {

    lateinit var adapter: IconAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            val str:String?=bundle.getString("iconset")
            getIcon(str)
        }
    }
    private fun getIcon(iconset:String?) {

        val icons = IconService.IconInstance.getIcons(iconset!!)

        icons.enqueue(object: Callback<icons> {
            override fun onResponse(call: Call<icons>, response: Response<icons>) {
                val icons = response.body()
                if (icons != null) {

                    adapter= IconAdapter(activity as MainActivity,icons.icons)
                    iconsList.adapter=adapter

                    iconsList.layoutManager= LinearLayoutManager(activity)
                    adapter.setOnItemClickListener(object: IconAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val bundle = Bundle()
                            bundle.putSerializable("icon",icons.icons[position])
                             findNavController().navigate(
                                 R.id.action_iconsFragment_to_downloadFragment,bundle)//,
                        }
                    })
                }
            }
            override fun onFailure(call: Call<icons>, t: Throwable) {
                Log.d("error", "Error in fetching icons", t)
            }
        })
    }
}