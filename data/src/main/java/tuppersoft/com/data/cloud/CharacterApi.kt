package tuppersoft.com.data.cloud

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import tuppersoft.com.domain.entities.CharacterDto
import tuppersoft.com.domain.entities.ResponseDto

interface CharacterApi {

    @GET("/api/character/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Long): Call<CharacterDto>

    @GET("/api/character/")
    fun getCharacters(): Call<ResponseDto>

}