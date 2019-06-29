package tuppersoft.com.adoptme.core.di.repositories

import dagger.Module
import dagger.Provides
import tuppersoft.com.data.repositories.FirebaseRepositoryImpl
import tuppersoft.com.domain.usescases.GetUser
import javax.inject.Singleton


@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideGetUser(dataSource: FirebaseRepositoryImpl): GetUser = GetUser(dataSource)

}

