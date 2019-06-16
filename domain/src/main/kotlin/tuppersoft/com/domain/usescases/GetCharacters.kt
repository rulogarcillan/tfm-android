package tuppersoft.com.domain.usescases

import arrow.core.Either
import arrow.core.None
import tuppersoft.com.domain.entities.CharacterDto
import tuppersoft.com.domain.exception.Failure
import tuppersoft.com.domain.repositories.CharacterRepository

class GetCharacters constructor(val repository: CharacterRepository) :

    UseCase<Either<Failure, MutableList<CharacterDto>>, None>() {

    override suspend fun run(params: None): Either<Failure, MutableList<CharacterDto>> {
        return repository.getCharacters()
    }
}