package tuppersoft.com.adoptme.features.home

import androidx.lifecycle.MutableLiveData
import arrow.core.None
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.usescases.GetAllRecords
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAllRecords: GetAllRecords) : GlobalViewModel() {

    val records: MutableLiveData<MutableList<RecordDto>> = MutableLiveData()

    fun getRecords() {

        getAllRecords.invoke(None) {
            records.value = it
        }
    }
}