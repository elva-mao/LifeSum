package com.elva.lifesum.di

import com.elva.lifesum.network.BASE_URL
import com.elva.lifesum.network.BaseInterceptor
import com.elva.lifesum.network.FoodDataSource
import com.elva.lifesum.network.IFoodService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(BaseInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodService(retrofit: Retrofit): IFoodService {
        return retrofit.create(IFoodService::class.java)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(foodService: IFoodService): FoodDataSource {
        return FoodDataSource(foodService)
    }
}