package com.wl.wanandroid.retrofit

import com.google.gson.Gson
import com.wl.wanandroid.utils.AppConstants
import com.wl.wanandroid.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class GetRetrofitService {
     companion object{
     var client: OkHttpClient? = null
    val TIMEOUT = 10
    val retrofitService: RetrofitService
        get() {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .baseUrl(AppConstants.BASEROOTURL)
                    .build()

            return retrofit.create(RetrofitService::class.java)
        }




     fun init() {
         val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
             override fun log(message: String) {
                 //打印retrofit日志
                 LogUtils.d("RetrofitLog", "retrofitBack = $message")
             }
         })

         loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
                .build()
    }


     }
}
