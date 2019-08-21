package tuppersoft.com.domain.entities

import java.util.Date

data class MessageDto(val msg: String = "", val uid: String = "",val timeStamp: Date? = null)