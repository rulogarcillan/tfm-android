package tuppersoft.com.domain.repositories

import arrow.core.None
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.MessageDto
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.UserDto

interface FirebaseRepository {

    fun getUserLogin(): UserDto?
    fun saveUserDataBase(user: UserDto): UserDto
    suspend fun getUserDataBase(userId: String): UserDto?
    suspend fun loginWithFirebase(token: String): UserDto?
    fun saveRecord(record: RecordDto): RecordDto
    suspend fun getAllRecords(animal: Animal?): List<RecordDto>
    fun saveChatMessage(msg: MessageDto, chatId: String): None
    suspend fun getUserChatsDataBase(userId: String): MutableList<String>
    fun deleteRecord(record: RecordDto)
}