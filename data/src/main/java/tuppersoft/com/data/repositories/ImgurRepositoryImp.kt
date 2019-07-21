package tuppersoft.com.data.repositories

import arrow.core.Either
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import tuppersoft.com.data.cloud.ImgurApi
import tuppersoft.com.domain.entities.Data
import tuppersoft.com.domain.entities.ImgDto
import tuppersoft.com.domain.exception.Failure
import tuppersoft.com.domain.repositories.ImgurRepository
import java.io.File
import javax.inject.Inject


class ImgurRepositoryImp @Inject constructor(private val service: ImgurApi) : ImgurRepository {

    override fun uploadImage(file: File, isConnected: Boolean): Either<Failure, ImgDto> {


        val mFile = MultipartBody.Part.createFormData(
            "image",
            file.name,
            RequestBody.create(MediaType.parse("image/*"), file)
        )
        //Base64 image
        return when (isConnected) {
            true -> request(service.uploadImg(mFile), { it }, ImgDto(Data("")))
            false, null -> Either.Left(Failure.NetworkConnection)
        }
    }

    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}