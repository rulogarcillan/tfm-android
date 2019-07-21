package tuppersoft.com.adoptme.core.di.repositories

import dagger.Module
import dagger.Provides
import tuppersoft.com.data.repositories.FirebaseRepositoryImpl
import tuppersoft.com.data.repositories.ImgurRepositoryImp
import tuppersoft.com.domain.usescases.DoLogin
import tuppersoft.com.domain.usescases.IsLogin
import tuppersoft.com.domain.usescases.SaveUser
import tuppersoft.com.domain.usescases.UploadImage
import javax.inject.Singleton


@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideIsLogin(dataSource: FirebaseRepositoryImpl): IsLogin = IsLogin(dataSource)


    @Provides
    @Singleton
    fun provideDoLogin(dataSource: FirebaseRepositoryImpl): DoLogin = DoLogin(dataSource)


    @Provides
    @Singleton
    fun provideSaveUser(dataSource: FirebaseRepositoryImpl): SaveUser = SaveUser(dataSource)

    @Provides
    @Singleton
    fun provideUploadImage(dataSource: ImgurRepositoryImp): UploadImage = UploadImage(dataSource)
}

