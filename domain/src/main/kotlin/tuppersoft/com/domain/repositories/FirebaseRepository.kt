package tuppersoft.com.domain.repositories

import tuppersoft.com.domain.entities.UserDto

interface FirebaseRepository {

    fun getUserLogin(): UserDto?

}