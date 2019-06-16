package tuppersoft.com.data.repositories

import arrow.core.Either
import tuppersoft.com.data.cloud.CharacterApi
import tuppersoft.com.data.cloud.request
import tuppersoft.com.domain.entities.CharacterDto
import tuppersoft.com.domain.exception.Failure
import tuppersoft.com.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(val characterApi: CharacterApi) : CharacterRepository {

    override fun getCharacter(idCharacter: Long): Either<Failure, CharacterDto> {
        return request(characterApi.getCharacter(idCharacter)) { it }
    }

    override fun getCharacters(): Either<Failure, MutableList<CharacterDto>> {
        return request(characterApi.getCharacters()) { it.results }
    }
}