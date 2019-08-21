package tuppersoft.com.domain.usescases

import arrow.core.None
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.repositories.FirebaseRepository

class DeleteRecord constructor(private val repository: FirebaseRepository) : UseCase<None, DeleteRecord.Params>() {

    override suspend fun run(params: Params): None {
        repository.deleteRecord(params.recordDto)

        return None
    }

    data class Params(val recordDto: RecordDto)
}
