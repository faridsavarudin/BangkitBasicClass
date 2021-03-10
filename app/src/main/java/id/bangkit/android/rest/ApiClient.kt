package id.bangkit.android

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private  var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient().newBuilder()

                .addInterceptor { chain ->
                var originalRequest = chain.request()
                val httpUrl = originalRequest.url
                    .newBuilder()
                    .build()

                originalRequest = originalRequest.newBuilder()
                    .url(httpUrl)
                    .addHeader("Authorization","token ${BuildConfig.TOKEN}")
                    .build()

                chain.proceed(originalRequest)
            }.addInterceptor(logging).build()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

}