package tuppersoft.com.domain.entities

data class RecordDto(
    val uid: String = "",
    val animal: Animal = Animal.DOG,
    val sex: Sex = Sex.MALE,
    val name: String = "",
    val age: Int = -1,
    val imageUrl: MutableList<String> = mutableListOf()
)