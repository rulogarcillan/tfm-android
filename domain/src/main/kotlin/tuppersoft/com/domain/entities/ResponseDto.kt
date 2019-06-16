package tuppersoft.com.domain.entities

data class ResponseDto (
    val info: InfoDto,
    val results: MutableList<CharacterDto>
)

