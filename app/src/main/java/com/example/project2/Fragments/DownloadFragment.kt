package com.example.project2.Fragments

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project2.Adapters.DownloadFragmentAdapter
import com.example.project2.Adapters.IconAdapter
import com.example.project2.Categoriesdataclasses.RasterSize
import com.example.project2.Categoriesdataclasses.icons
import com.example.project2.IconService
import com.example.project2.MainActivity
//import com.example.project2.DownloadFragmentArgs
import com.example.project2.R
import kotlinx.android.synthetic.main.download_fragment_layout.*
import kotlinx.android.synthetic.main.icons_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File



class DownloadFragment: Fragment(R.layout.download_fragment_layout) {

    lateinit var adapter: DownloadFragmentAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle == null) {
            Log.e("Icon", "No information")
            return
        }
        val args = DownloadFragmentArgs.fromBundle(bundle)
        getRaster(args.icon.raster_sizes)
    }

    private fun getRaster(rasters: List<RasterSize>) {

        adapter = DownloadFragmentAdapter(activity as MainActivity, rasters)
        rasterList.adapter = adapter

        rasterList.layoutManager = LinearLayoutManager(activity)
        adapter.setOnItemClickListener(object : DownloadFragmentAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                downloadImageNew(rasters[position].size.toString(),rasters[position].formats[0].preview_url)

            }
        })
    }
    private fun downloadImageNew(filename: String, downloadUrlOfImage: String) {
        try {
            val dm = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
            val downloadUri: Uri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/png") // Your file type. You can use this code to download other file types also.
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + filename + ".png"
                )
            dm!!.enqueue(request)
            Toast.makeText(activity, "Image download started.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show()
            Log.d("failure", e.toString())
        }
    }
}
