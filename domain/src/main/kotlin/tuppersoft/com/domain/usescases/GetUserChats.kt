package tuppersoft.com.domain.usescases

import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class GetUserChats constructor(private val repository: FirebaseRepository) :

    UseCase<MutableList<UserDto>, GetUserChats.Params>() {

    override suspend fun run(params: Params): MutableList<UserDto> {
        val list = repository.getUserChatsDataBase(params.uid)
        val listUser: MutableList<UserDto> = mutableListOf()
        list.forEach { chatId ->
            repository.getUserDataBase(chatId.split("-").filterNot { it.contentEquals(params.uid) }[0])?.let {
                listUser.add(it)
            }
        }
        return listUser
    }

    data class Params(val uid: String)
}