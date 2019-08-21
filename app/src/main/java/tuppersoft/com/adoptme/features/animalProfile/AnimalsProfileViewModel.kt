package tuppersoft.com.adoptme.features.animalProfile

import androidx.lifecycle.MutableLiveData
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.DeleteRecord
import tuppersoft.com.domain.usescases.DeleteRecord.Params
import tuppersoft.com.domain.usescases.GetUser
import javax.inject.Inject

class AnimalsProfileViewModel @Inject constructor(
    private val getUser: GetUser,
    private val deleteRecord: DeleteRecord
) : GlobalViewModel() {

    val user: MutableLiveData<UserDto> = MutableLiveData()
    val flag: MutableLiveData<Boolean> = MutableLiveData()

    fun getUser(uid: String) {
        getUser.invoke(GetUser.Params(uid)) {
            user.value = it
        }
    }

    fun deleteRecord(recordDto: RecordDto) {
        deleteRecord.invoke(Params(recordDto)) {
            flag.value = false
        }
    }
}