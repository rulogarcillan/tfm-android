package tuppersoft.com.domain.usescases

import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class GetUser constructor(private val repository: FirebaseRepository) : UseCase<UserDto?, GetUser.Params>() {

    override suspend fun run(params: Params): UserDto? {

        return repository.getUserDataBase(params.uid)
    }

    data class Params(val uid: String)
}