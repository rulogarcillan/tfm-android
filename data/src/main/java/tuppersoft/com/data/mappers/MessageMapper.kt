package tuppersoft.com.data.mappers

import tuppersoft.com.data.entities.Message
import tuppersoft.com.domain.entities.MessageDto

fun MessageDto.toMessage() = Message(
    msg,
    getReference(uid)
)

fun Message.toMessageDto() = MessageDto(
    msg,
    uid?.id.toString(),
    timeStamp?.toDate()
)