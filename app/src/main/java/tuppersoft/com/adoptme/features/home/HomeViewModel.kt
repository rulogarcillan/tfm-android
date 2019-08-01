package tuppersoft.com.adoptme.features.home

import androidx.lifecycle.MutableLiveData
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.Animal.CAT
import tuppersoft.com.domain.entities.Animal.DOG
import tuppersoft.com.domain.entities.Animal.OTHER
import tuppersoft.com.domain.entities.Animal.RABBIT
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.usescases.GetAllRecordsByType
import tuppersoft.com.domain.usescases.GetAllRecordsByType.Params
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAllRecords: GetAllRecordsByType) : GlobalViewModel() {

    val records: MutableLiveData<MutableList<RecordDto>> = MutableLiveData()
    val dogs: MutableLiveData<MutableList<RecordDto>> = MutableLiveData()
    val cats: MutableLiveData<MutableList<RecordDto>> = MutableLiveData()
    val rabbits: MutableLiveData<MutableList<RecordDto>> = MutableLiveData()
    val others: MutableLiveData<MutableList<RecordDto>> = MutableLiveData()

    fun getAllRecords(animal: Animal?) {
        getAllRecords.invoke(Params(animal)) {
            when (animal) {
                DOG -> dogs.value = it
                CAT -> cats.value = it
                RABBIT -> rabbits.value = it
                OTHER -> others.value = it
                else -> records.value = it
            }
        }
    }
}