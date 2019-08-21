package tuppersoft.com.domain.usescases

import arrow.core.None
import tuppersoft.com.domain.entities.MessageDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class SendMessage constructor(private val repository: FirebaseRepository) : UseCase<None, SendMessage.Params>() {

    override suspend fun run(params: Params): None {
        return repository.saveChatMessage(params.msg, params.chatId)
    }

    data class Params(val msg: MessageDto, val chatId: String)
}
