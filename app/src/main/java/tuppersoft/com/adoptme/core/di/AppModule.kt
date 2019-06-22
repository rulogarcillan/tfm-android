package tuppersoft.com.adoptme.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tuppersoft.com.adoptme.BuildConfig
import tuppersoft.com.data.cloud.CharacterApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/"
    }

    @Provides
    @Singleton
    fun provideAppContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun createNetworkClient() = retrofitClient(httpClient())

    @Provides
    @Singleton
    fun provideCharacterApi(): CharacterApi = retrofitClient(httpClient()).create(CharacterApi::class.java)

    private fun httpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    private fun retrofitClient(httpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}



