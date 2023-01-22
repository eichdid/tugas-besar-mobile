package com.example.tugas_mobile.data.api

import com.example.tugas_mobile.data.model.ListResponse
import com.example.tugas_mobile.data.model.MovieResponse
import com.example.tugas_mobile.data.model.TvShowResponse
import com.example.tugas_mobile.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?api_key=$API_KEY")
    fun getMovies(): Call<ListResponse<MovieResponse>>

    @GET("tv/airing_today?api_key=$API_KEY")
    fun getTvShow(): Call<ListResponse<TvShowResponse>>

}