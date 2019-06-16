package tuppersoft.com.burguerfinder.core.di.repositories

import dagger.Module
import dagger.Provides
import tuppersoft.com.data.repositories.CharacterRepositoryImpl
import tuppersoft.com.domain.usescases.GetCharacter
import tuppersoft.com.domain.usescases.GetCharacters
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideGetCharacter(dataSource: CharacterRepositoryImpl): GetCharacter = GetCharacter(dataSource)

    @Provides
    @Singleton
    fun provideGetCharacters(dataSource: CharacterRepositoryImpl): GetCharacters = GetCharacters(dataSource)
}

