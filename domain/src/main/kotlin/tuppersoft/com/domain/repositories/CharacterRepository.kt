package tuppersoft.com.domain.repositories

import arrow.core.Either
import tuppersoft.com.domain.entities.CharacterDto
import tuppersoft.com.domain.exception.Failure

interface CharacterRepository {

    fun getCharacter(idCharacter: Long): Either<Failure, CharacterDto>
    fun getCharacters(): Either<Failure, MutableList<CharacterDto>>

}