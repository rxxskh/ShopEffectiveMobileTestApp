package com.rxs.shopeffectivemobile.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.rxs.shopeffectivemobile.common.BASE_URL
import com.rxs.shopeffectivemobile.common.PREFS_KEY
import com.rxs.shopeffectivemobile.data.datasource.local.LocalDataSource
import com.rxs.shopeffectivemobile.data.datasource.local.SharedPreferencesSource
import com.rxs.shopeffectivemobile.data.datasource.remote.ShopApi
import com.rxs.shopeffectivemobile.data.repository.ShopRepositoryImpl
import com.rxs.shopeffectivemobile.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideShopApi(): ShopApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(ShopApi::class.java)

    @Provides
    @Singleton
    fun provideLocalDataSource(gson: Gson, sharedPreferences: SharedPreferences): LocalDataSource =
        SharedPreferencesSource(sharedPreferences = sharedPreferences, gson = gson)

    @Provides
    @Singleton
    fun provideShopRepository(
        remoteApi: ShopApi,
        localDataSource: LocalDataSource
    ): ShopRepository =
        ShopRepositoryImpl(remoteApi = remoteApi, localDataSource = localDataSource)
}