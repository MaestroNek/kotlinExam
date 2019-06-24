package com.example.krutik_exam

import com.example.krutik_exam.GetData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import io.reactivex.Observable

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

class NetHelper {
    lateinit var requestInterface: Retrofit

    companion object {
        val instance = NetHelper()

        const val BASE_URL = "https://gateway.marvel.com/"
        const val publicKey = "59c40e91806e202664f5612b32c3ba67"
        const val privateKey = "0a7125d86ba8e1cad416413e439916a215f6002e"

    }

    init {
        initClient()
    }

    private fun initClient() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        requestInterface = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okClient)
            .build()
    }

    fun getComics(): Observable<ComicsResponse> {

        // ts - a timestamp (or other long string which can change on a request-by-request basis)
        val ts = 1
        val hash = "$ts$privateKey$publicKey".md5()
        val interfaceObj = requestInterface.create(GetData::class.java)
        return interfaceObj.getData(publicKey,ts,hash)
    }

}
