package tuppersoft.com.domain.repositories

import arrow.core.Either
import tuppersoft.com.domain.entities.ImgDto
import tuppersoft.com.domain.exception.Failure
import java.io.File

interface ImgurRepository {
    fun uploadImage(file: File, isConnected: Boolean): Either<Failure, ImgDto>
}