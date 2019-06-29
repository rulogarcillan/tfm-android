package tuppersoft.com.domain.entities

data class UserDto(
    val uid: String,
    val name: String,
    val email: String,
    val photoUrl: String,
    val emailVerified: Boolean,
    val description: String = ""
)


