package tuppersoft.com.adoptme.core.di.repositories

import dagger.Module
import dagger.Provides
import tuppersoft.com.data.repositories.FirebaseRepositoryImpl
import tuppersoft.com.domain.usescases.IsLogin
import tuppersoft.com.domain.usescases.DoLogin
import javax.inject.Singleton


@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideGetUser(dataSource: FirebaseRepositoryImpl): IsLogin = IsLogin(dataSource)


    @Provides
    @Singleton
    fun provideLogin(dataSource: FirebaseRepositoryImpl): DoLogin = DoLogin(dataSource)
}

