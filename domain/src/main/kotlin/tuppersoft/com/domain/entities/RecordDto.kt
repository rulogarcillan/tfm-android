package tuppersoft.com.domain.entities

data class RecordDto(
    val animal: Animal = Animal.DOG,
    val name: String = "",
    val age: Int = -1,
    val sex: Sex = Sex.MALE,
    val imageUrl: MutableList<String> = mutableListOf()
)