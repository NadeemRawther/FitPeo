package com.nads.fitpeo.di


import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.nads.fitpeo.data.datasource.DataSource
import com.nads.fitpeo.data.datasource.FitPeoDataSource
import com.nads.fitpeo.data.repo.FitPeoDefaultRepo
import com.nads.fitpeo.data.repo.FitPeoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FitPeoModule {


    @Singleton
    @Provides
    fun provideRepository(fitPeoDataSource:DataSource):FitPeoRepository{
        return FitPeoDefaultRepo(fitPeoDataSource)
    }

    @Singleton
    @Provides
    fun provideCoroutine(): CoroutineDispatcher {
        return Dispatchers.IO
    }
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor.invoke { chain: Interceptor.Chain ->
                val orginal: Request = chain.request()
                val request = orginal.newBuilder().method(
                    orginal.method,
                    orginal.body
                )
                    .build()
                chain.proceed(request)
            }).build()
        return client
    }
    @Singleton
    @Provides
    fun providesRetrofitClient(): DataSource {
//        val gson = GsonBuilder()
//            .registerTypeAdapter(Int::class.javaPrimitiveType, IntTypeAdapter())
//            .registerTypeAdapter(Int::class.java, IntTypeAdapter()).create()
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(providesOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FitPeoDataSource::class.java)
    }



}