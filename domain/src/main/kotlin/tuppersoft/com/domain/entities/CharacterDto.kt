package tuppersoft.com.domain.entities

data class CharacterDto(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationDto,
    val location: LocationDto,
    val image: String,
    val episode: MutableList<String>,
    val url: String,
    val created: String
)

