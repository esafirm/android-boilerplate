package com.esafirm.androidboilerplate.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.esafirm.androidboilerplate.data.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module class NetworkModule {

	private val ENDPOINT = "https://api.ribot.io/"

	@Singleton @Provides internal fun provideGson(): Gson {
		return GsonBuilder()
				.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
				.create()
	}

	@Singleton @Provides internal fun provideOkHttpClient(): OkHttpClient {
		val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
		loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

		return OkHttpClient.Builder()
				.connectTimeout(40, TimeUnit.SECONDS)
				.writeTimeout(40, TimeUnit.SECONDS)
				.readTimeout(40, TimeUnit.SECONDS)
				.addInterceptor(loggingInterceptor)
				.build()
	}

	@Singleton @Provides internal fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
				.client(okHttpClient)
				.baseUrl(ENDPOINT)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build()
	}

	/* --------------------------------------------------- */
	/* > Retrofit Interfaces*/
	/* --------------------------------------------------- */

	@Singleton @Provides fun provideApiService(retrofit: Retrofit): ApiService {
		return retrofit.create(ApiService::class.java)
	}
}
