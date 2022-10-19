package com.example.project2

import com.example.project2.Categoriesdataclasses.Categories
import com.example.project2.Categoriesdataclasses.icons
import com.example.project2.IconsetDataClass.Iconsets
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL="https://api.iconfinder.com/"

interface IconInterface{
    @Headers(
        "Accept: application/json",
        "Authorization: Bearer X0vjEUN6KRlxbp2DoUkyHeM0VOmxY91rA6BbU5j3Xu6wDodwS0McmilLPBWDUcJ1"
    )
    @GET("v4/icons/search")
    fun searchIcons(@Query("query") query: String): Call<icons>


    @Headers(
        "Accept: application/json",
        "Authorization: Bearer X0vjEUN6KRlxbp2DoUkyHeM0VOmxY91rA6BbU5j3Xu6wDodwS0McmilLPBWDUcJ1"
    )
    @GET("v4/categories?count=100")
    fun getCategories(): Call<Categories>


    @Headers(
        "Accept: application/json",
        "Authorization: Bearer X0vjEUN6KRlxbp2DoUkyHeM0VOmxY91rA6BbU5j3Xu6wDodwS0McmilLPBWDUcJ1"
    )
    @GET("v4/categories/{category}/iconsets?count=100")
    fun getIconsets(@Path("category") category:String): Call<Iconsets>

    @Headers(
        "Accept: application/json",
        "Authorization: Bearer X0vjEUN6KRlxbp2DoUkyHeM0VOmxY91rA6BbU5j3Xu6wDodwS0McmilLPBWDUcJ1"
    )
    @GET("v4/iconsets/{iconset_id}/icons?count=100")
    fun getIcons(@Path("iconset_id") iconset_id:String): Call<icons>
}

object IconService{
    val IconInstance:IconInterface
    init{

        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
       IconInstance=retrofit.create(IconInterface::class.java)
    }
}