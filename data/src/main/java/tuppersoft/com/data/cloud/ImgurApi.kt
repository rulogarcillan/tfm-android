package tuppersoft.com.data.cloud

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import tuppersoft.com.domain.entities.ImgDto

interface ImgurApi {
    @Multipart
    @POST("/3/upload")
    fun uploadImg(@Part file: MultipartBody.Part): Call<ImgDto>

}