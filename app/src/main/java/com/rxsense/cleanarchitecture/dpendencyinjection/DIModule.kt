package com.rxsense.cleanarchitecture.dpendencyinjection

import android.content.Context
import coil.ImageLoader
import coil.request.CachePolicy
import coil.util.CoilUtils
import com.rxsense.cleanarchitecture.BuildConfig
import com.rxsense.cleanarchitecture.network.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 */
const val CONNECTION_TIMEOUT: Long = 60
const val READ_WRITE_TIMEOUT: Long = 120

@Module(
    includes = [
        ViewModelBuilder::class,
        ActivityBuilder::class,
        ActivityViewModelBuilder::class]
)
class DIModule(val context: Context) {
    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(context))
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .bitmapPoolingEnabled(true)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideMemberApiRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.baseUrl)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(RequestInterceptor())
            .addInterceptor(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                } else {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            )
            .build()
    }
}