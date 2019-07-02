package tuppersoft.com.domain.usescases

import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class SaveUser constructor(private val repository: FirebaseRepository) : UseCase<UserDto, SaveUser.Params>() {

    override suspend fun run(params: Params): UserDto {
        return repository.saveUserDataBase(params.user)
    }

    data class Params(val user: UserDto)
}
