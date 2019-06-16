package tuppersoft.com.burguerfinder.core.di

import dagger.Component
import tuppersoft.com.burguerfinder.App
import tuppersoft.com.burguerfinder.core.di.repositories.RepositoriesModule
import tuppersoft.com.burguerfinder.core.di.viewmodel.ViewModelModule
import tuppersoft.com.burguerfinder.features.main.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoriesModule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

}