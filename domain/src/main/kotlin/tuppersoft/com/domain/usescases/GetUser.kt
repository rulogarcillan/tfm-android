package tuppersoft.com.domain.usescases

import arrow.core.None
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class GetUser constructor(private val repository: FirebaseRepository) : UseCase<UserDto?, None>() {

    override suspend fun run(params: None): UserDto? {
        return repository.getUserLogin()
    }
}