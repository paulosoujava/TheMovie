package com.paulo.mymovie.data.repositories.cache

import android.content.Context
import com.paulo.mymovie.domain.util.isOnline
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**********************************************************************************
addInterceptor:
usado para adicionar o interceptor no nível do aplicativo.
addNetworkInterceptor:
Como o nome diz, usado para adicionar o interceptor no nível da rede.
 ***********************************************************************************/
class ForceCacheInterceptor(
    val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!isOnline(context)) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}
