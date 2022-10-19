package com.example.project2

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
    }
   private fun hasWriteExternalStoragePermission()=
       ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
   private fun requestPermissions(){
       var permissionsToRequest=mutableListOf<String>()
       if(!hasWriteExternalStoragePermission()){
           permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
       }
       if(permissionsToRequest.isNotEmpty()){
           ActivityCompat.requestPermissions(this,permissionsToRequest.toTypedArray(),0)
       }
   }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty()){
             for(i in grantResults.indices){
                 if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                     Log.d("PermissionsRequest","${permissions[i]} granted.")
                 }
             }
        }
    }



}

