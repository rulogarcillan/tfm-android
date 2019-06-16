package tuppersoft.com.tfm.core.di

import dagger.Component
import tuppersoft.com.tfm.App
import tuppersoft.com.tfm.core.di.repositories.RepositoriesModule
import tuppersoft.com.tfm.core.di.viewmodel.ViewModelModule
import tuppersoft.com.tfm.features.main.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoriesModule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

}