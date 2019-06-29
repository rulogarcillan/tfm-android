package tuppersoft.com.adoptme.features.login

import androidx.lifecycle.MutableLiveData
import arrow.core.None
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.GetUser
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val getUser: GetUser) : GlobalViewModel() {

    val userDto: MutableLiveData<UserDto> = MutableLiveData()

    init {
        getUser.invoke(None) {
            userDto.value = it
        }
    }
}