package com.example.project2.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.Adapters.SearchIconAdapter
import com.example.project2.IconService
import com.example.project2.MainActivity
import com.example.project2.R
import com.example.project2.Categoriesdataclasses.icons
import kotlinx.android.synthetic.main.icons_fragment.*
import kotlinx.android.synthetic.main.searchicon_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchIcon_Fragment: Fragment(R.layout.searchicon_fragment), CoroutineScope {

    override val coroutineContext = Dispatchers.Main
    lateinit var adapter: SearchIconAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBar.addTextChangedListener(object:TextWatcher{
            private var searchFor = ""
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val searchText = p0.toString()
                if (searchText == searchFor)
                    return

                searchFor = searchText

                launch {
                    delay(450)
                    if (searchText != searchFor)
                        return@launch
                    getIcon(p0.toString())
                }
            }
        })

    }
    private fun getIcon(query:String?) {

        val icons = IconService.IconInstance.searchIcons(query!!)

        icons.enqueue(object: Callback<icons> {
            override fun onResponse(call: Call<icons>, response: Response<icons>) {

                val icons = response.body()
                if (icons != null) {

                    adapter= SearchIconAdapter(activity as MainActivity,icons.icons)
                    iconsList1.adapter=adapter

                    iconsList1.layoutManager= LinearLayoutManager(activity)
                    adapter.setOnItemClickListener(object: SearchIconAdapter.onItemClickListener{

                        override fun onItemClick(position: Int) {
                            //Toast.makeText(activity,icons.icons[position].icon_id.toString(), Toast.LENGTH_SHORT).show()
                            val bundle = Bundle()
                            bundle.putSerializable("icon",icons.icons[position])
                            findNavController().navigate(
                                R.id.action_searchIcon_Fragment_to_downloadFragment,bundle)
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