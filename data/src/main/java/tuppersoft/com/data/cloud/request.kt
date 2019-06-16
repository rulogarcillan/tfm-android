package tuppersoft.com.data.cloud

import arrow.core.Either
import retrofit2.Call
import tuppersoft.com.domain.exception.Failure
import java.io.IOException
import java.net.UnknownHostException

fun <T, R> request(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful && response.body() != null) {
            true -> Either.Right(transform((response.body()!!)))
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: Throwable) {
        when (exception) {
            is UnknownHostException -> Either.Left(Failure.ServerError)
            is IOException -> Either.Left(Failure.NetworkConnection)
            else -> Either.Left(Failure.ServerError)
        }
    }
}