package tuppersoft.com.domain.usescases

import arrow.core.Either
import tuppersoft.com.domain.entities.CharacterDto
import tuppersoft.com.domain.exception.Failure
import tuppersoft.com.domain.repositories.CharacterRepository

class GetCharacter constructor(val repository: CharacterRepository) :

    UseCase<Either<Failure, CharacterDto>, GetCharacter.Param>() {

    override suspend fun run(params: Param): Either<Failure, CharacterDto> {
        return repository.getCharacter(params.idCharacter)
    }

    data class Param(val idCharacter: Long)
}