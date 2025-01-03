package com.example.careerlink.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return "https://apicareerlink-production.up.railway.app/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
//        val logging = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideTokenDataStore(
//        @ApplicationContext context: Context
//    ): TokenDataStore = TokenDataStore(context)

    @Provides
    @Singleton
    fun provideLokerApiService(retrofit: Retrofit): LokerApiService {
        return retrofit.create(LokerApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMagangApiService(retrofit: Retrofit): MagangApiService {
        return retrofit.create(MagangApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSertifikasiApiService(retrofit: Retrofit): SertifikasiApiService {
        return retrofit.create(SertifikasiApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePenggunaApiService(retrofit: Retrofit): PenggunaApiService {
        return retrofit.create(PenggunaApiService::class.java)
    }

}