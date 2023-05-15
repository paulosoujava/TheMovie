package com.paulo.mymovie.data.repositories.cache

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File



fun okHttpClient(context: Context)  =OkHttpClient().newBuilder()
    .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB
    .addNetworkInterceptor(CacheInterceptor())
    .addInterceptor(ForceCacheInterceptor(context))
    .build()