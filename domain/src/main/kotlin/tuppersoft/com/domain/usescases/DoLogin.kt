package tuppersoft.com.domain.usescases

import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class DoLogin constructor(private val repository: FirebaseRepository) : UseCase<UserDto?, DoLogin.Params>() {

    override suspend fun run(params: Params): UserDto? {
        val userLogin = repository.loginWithFirebase(params.token)
        if (userLogin != null) {
            return repository.getUserDataBase(userLogin.uid) ?: repository.saveUserDataBase(userLogin)
        }
        return null
    }

    data class Params(val token: String)
}