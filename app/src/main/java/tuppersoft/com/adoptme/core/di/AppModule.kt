package tuppersoft.com.adoptme.core.di

import android.app.Application
import android.content.Context
import com.google.common.net.HttpHeaders.AUTHORIZATION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tuppersoft.com.adoptme.BuildConfig
import tuppersoft.com.data.cloud.ImgurApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {


    @Provides
    @Singleton
    fun provideAppContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseDb(): FirebaseFirestore = FirebaseFirestore.getInstance()


    @Provides
    @Singleton
    fun createNetworkClient() = retrofitClient(httpClient())

    @Provides
    @Singleton
    fun provideImgurApi(): ImgurApi = retrofitClient(httpClient()).create(ImgurApi::class.java)


    private fun httpClient(): OkHttpClient {
        // val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.addInterceptor(
            HttpLoggingInterceptor().setLevel(
                getLogLevel()
            )
        )

        clientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header(AUTHORIZATION, "Client-ID 0465efa969a97ef")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }

        clientBuilder.readTimeout(30, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(30, TimeUnit.SECONDS)
        return clientBuilder.build()
    }

    private fun retrofitClient(httpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLogLevel(): HttpLoggingInterceptor.Level {
        return if (tuppersoft.com.data.BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.HEADERS
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}