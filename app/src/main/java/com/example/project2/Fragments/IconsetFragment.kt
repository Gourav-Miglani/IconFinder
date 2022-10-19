package com.example.project2.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.Adapters.IconSetAdapter
import com.example.project2.IconService
import com.example.project2.IconsetDataClass.Iconsets
import com.example.project2.MainActivity
import com.example.project2.R
import kotlinx.android.synthetic.main.iconset_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IconsetFragment: Fragment(R.layout.iconset_fragment)  {

    lateinit var adapter: IconSetAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            val str:String?=bundle.getString("category")
            getIconset(str)
        }
    }
    private fun getIconset(category:String?) {

        val iconsets = IconService.IconInstance.getIconsets(category!!)

        iconsets.enqueue(object: Callback<Iconsets> {
            override fun onResponse(call: Call<Iconsets>, response: Response<Iconsets>) {
                val iconsets = response.body()
                if (iconsets != null) {

                    Log.d("Success", iconsets.toString())
                    adapter= IconSetAdapter(activity as MainActivity,iconsets.iconsets)
                    IconsetList.adapter=adapter

                    IconsetList.layoutManager= LinearLayoutManager(activity)
                    adapter.setOnItemClickListener(object: IconSetAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val bundle = Bundle()
                            bundle.putString("iconset",iconsets.iconsets[position].iconset_id.toString())

                            findNavController().navigate(
                                R.id.action_iconsetFragment_to_iconsFragment,
                                bundle)
                        }

                    })

                }
            }
            override fun onFailure(call: Call<Iconsets>, t: Throwable) {
                Log.d("error", "Error in fetching icons", t)
            }

        })
    }


}