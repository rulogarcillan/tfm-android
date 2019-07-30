package tuppersoft.com.domain.usescases

import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class SaveRecord constructor(private val repository: FirebaseRepository) : UseCase<RecordDto, SaveRecord.Params>() {

    override suspend fun run(params: Params): RecordDto {

        return repository.saveRecord(params.record)
    }

    data class Params(val record: RecordDto)
}
