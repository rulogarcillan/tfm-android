package tuppersoft.com.domain.repositories

import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.UserDto

interface FirebaseRepository {

    fun getUserLogin(): UserDto?
    fun saveUserDataBase(user: UserDto): UserDto
    suspend fun getUserDataBase(userId: String): UserDto?
    suspend fun loginWithFirebase(token: String): UserDto?
    fun saveRecord(record: RecordDto): RecordDto
    suspend fun getAllRecords(): List<RecordDto>
}