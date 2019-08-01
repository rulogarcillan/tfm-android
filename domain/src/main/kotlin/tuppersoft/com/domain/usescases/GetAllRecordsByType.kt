package tuppersoft.com.domain.usescases

import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.repositories.FirebaseRepository
import tuppersoft.com.domain.usescases.GetAllRecordsByType.Params

class GetAllRecordsByType constructor(private val repository: FirebaseRepository) : UseCase<MutableList<RecordDto>, Params>() {

    override suspend fun run(params: Params): MutableList<RecordDto> {
        return repository.getAllRecords(params.animal).toMutableList()
    }

    data class Params(val animal: Animal?)
}