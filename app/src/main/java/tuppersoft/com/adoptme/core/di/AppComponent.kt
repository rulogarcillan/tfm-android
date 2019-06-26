package tuppersoft.com.adoptme.core.di

import dagger.Component
import tuppersoft.com.adoptme.App
import tuppersoft.com.adoptme.core.di.repositories.RepositoriesModule
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelModule
import tuppersoft.com.adoptme.features.login.LoginActivity
import tuppersoft.com.adoptme.features.profile.ProfileFragment
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoriesModule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(loginActivity: LoginActivity)
    fun inject(profileFragment: ProfileFragment)

}