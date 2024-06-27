package com.android.njtransit.di

import android.content.Context
import androidx.room.Room
import com.android.njtransit.BuildConfig


import com.android.njtransit.database.AppDatabase
import com.android.njtransit.network.APIClient
import com.android.njtransit.util.AppExecutors
import com.google.gson.GsonBuilder
import com.google.gson.internal.GsonBuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object Modules {

    //Database object
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase::DATABASE_NAME.name
        ).build()
    }

    //Ok http client is used to increase the default timeout duration
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    //Retrofit instance for network calls
    @Provides
    @Singleton
    fun retrofitInstance(okHttpClient: OkHttpClient, appExecutors: AppExecutors): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .callbackExecutor(appExecutors.networkIO())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    //Returns a single retrofit instance to the calling function
    @Provides
    fun provideApiClient(retrofit: Retrofit): APIClient {
        return retrofit.create(APIClient::class.java)
    }

}