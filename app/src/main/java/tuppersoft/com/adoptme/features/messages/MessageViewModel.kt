package tuppersoft.com.adoptme.features.messages

import androidx.lifecycle.MutableLiveData
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.GetUserChats
import tuppersoft.com.domain.usescases.GetUserChats.Params
import javax.inject.Inject

class MessageViewModel @Inject constructor(
    private val getUserChats: GetUserChats
) : GlobalViewModel() {

    val listChats: MutableLiveData<MutableList<UserDto>> = MutableLiveData()

    fun getUserChats(uid: String) {
        getUserChats.invoke(Params(uid)) {
            listChats.value = it
        }
    }
}
