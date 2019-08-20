package tuppersoft.com.adoptme.core.di

import dagger.Component
import tuppersoft.com.adoptme.App
import tuppersoft.com.adoptme.core.di.repositories.RepositoriesModule
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelModule
import tuppersoft.com.adoptme.features.animalProfile.AnimalProfileFragment
import tuppersoft.com.adoptme.features.add.AddActivity
import tuppersoft.com.adoptme.features.bookmarks.BookmarksFragment
import tuppersoft.com.adoptme.features.discovery.DiscoveryFragment
import tuppersoft.com.adoptme.features.home.HomeFragment
import tuppersoft.com.adoptme.features.login.LoginActivity
import tuppersoft.com.adoptme.features.messages.MessagesFragment
import tuppersoft.com.adoptme.features.personaldata.PersonalDataActivity
import tuppersoft.com.adoptme.features.profile.ProfileFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, RepositoriesModule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(loginActivity: LoginActivity)
    fun inject(activity: PersonalDataActivity)
    fun inject(activity: AddActivity)

    fun inject(fragment: HomeFragment)
    fun inject(fragment: DiscoveryFragment)
    fun inject(fragment: BookmarksFragment)
    fun inject(fragment: AnimalProfileFragment)
    fun inject(fragment: MessagesFragment)
    fun inject(fragment: ProfileFragment)
}