package tuppersoft.com.adoptme.features.personaldata

import androidx.lifecycle.MutableLiveData
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.SaveUser
import javax.inject.Inject

class PersonalDataViewModel @Inject constructor(private val saveUser: SaveUser) : GlobalViewModel() {

    val userDto: MutableLiveData<UserDto> = MutableLiveData()

    fun saveUSer(newUser: UserDto) {
        saveUser.invoke(SaveUser.Params(newUser)) {
            userDto.value = it
        }
    }
}