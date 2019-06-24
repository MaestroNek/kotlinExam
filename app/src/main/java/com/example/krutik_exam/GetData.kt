package com.example.krutik_exam

import com.example.krutik_exam.ComicsResponse
import io.reactivex.Observable
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetData {
    @GET("/v1/public/comics")
    fun getData(@Query("apikey") apikey: String,
                @Query("ts") ts: Int,
                @Query("hash") hash: String) : Observable<ComicsResponse>
}
