package tuppersoft.com.data.mappers

import com.google.firebase.auth.FirebaseUser
import tuppersoft.com.domain.entities.UserDto

fun FirebaseUser.toUser() = UserDto(
    uid,
    displayName ?: "",
    email ?: "",
    photoUrl.toString(),
    isEmailVerified

)

