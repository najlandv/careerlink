package com.example.careerlink.services

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://n6j4w26m-3000.asse.devtunnels.ms/")
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

}