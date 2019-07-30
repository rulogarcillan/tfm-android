package tuppersoft.com.domain.usescases

import arrow.core.Either
import tuppersoft.com.domain.entities.ImgDto
import tuppersoft.com.domain.exception.Failure
import tuppersoft.com.domain.repositories.ImgurRepository
import java.io.File

class UploadImage constructor(private val repository: ImgurRepository) : UseCase<Either<Failure, ImgDto>, UploadImage.Params>() {

    override suspend fun run(params: Params): Either<Failure, ImgDto> {
        return repository.uploadImage(params.file, params.isConnected)
    }

    data class Params(val file: File, val isConnected: Boolean)
}