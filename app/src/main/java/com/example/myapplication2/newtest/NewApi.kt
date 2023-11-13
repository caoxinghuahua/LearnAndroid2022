package com.example.myapplication2.newtest

import androidx.lifecycle.LiveData
import retrofit2.http.FieldMap
import retrofit2.http.POST

interface NewsApi {
    @POST("/wanandroid")
    fun fetchNewsLiveData(
        @FieldMap map: Map<String, String>
    ): LiveData<ApiResponse<NewsBean>>
}