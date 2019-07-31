package tuppersoft.com.domain.usescases

import arrow.core.None
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class GetAllRecords constructor(private val repository: FirebaseRepository) : UseCase<MutableList<RecordDto>, None>() {

    override suspend fun run(params: None): MutableList<RecordDto> {
        return repository.getAllRecords().toMutableList()
    }
}