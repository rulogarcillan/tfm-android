package tuppersoft.com.domain.entities

import java.io.Serializable

data class UserDto(
    var uid: String = "",
    var name: String = "",
    var email: String = "",
    var photoUrl: String = "",
    var emailVerified: Boolean = false,
    var description: String = "",
    var zip: String = "",
    var gender: String = ""
) : Serializable


