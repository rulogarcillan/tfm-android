package tuppersoft.com.adoptme.features.login

import androidx.lifecycle.MutableLiveData
import arrow.core.None
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.DoLogin
import tuppersoft.com.domain.usescases.IsLogin
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val isLogin: IsLogin, private val login: DoLogin) : GlobalViewModel() {

    val userDto: MutableLiveData<UserDto> = MutableLiveData()
    val logged: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isLogin()
    }

    private fun isLogin() {
        isLogin.invoke(None) {
            logged.value = it != null
        }
    }

    fun login(token: String) {
        login.invoke(DoLogin.Params(token)) {
            userDto.value = it
        }
    }
}