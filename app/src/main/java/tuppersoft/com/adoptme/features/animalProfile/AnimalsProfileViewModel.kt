package tuppersoft.com.adoptme.features.animalProfile

import androidx.lifecycle.MutableLiveData
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.GetUser
import javax.inject.Inject

class AnimalsProfileViewModel @Inject constructor(private val getUser: GetUser) : GlobalViewModel() {

    val user: MutableLiveData<UserDto> = MutableLiveData()

    fun getUser(uid: String) {
        getUser.invoke(GetUser.Params(uid)) {
            user.value = it
        }
    }
}