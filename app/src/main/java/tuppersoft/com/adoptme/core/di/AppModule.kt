package tuppersoft.com.adoptme.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

   /* companion object {
        const val BASE_URL = "https://rickandmortyapi.com/"
    }
*/
    @Provides
    @Singleton
    fun provideAppContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideApplication(): Application = app
}