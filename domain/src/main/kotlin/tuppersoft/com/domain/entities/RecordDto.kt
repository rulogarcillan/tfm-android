package tuppersoft.com.domain.entities

import java.io.Serializable

data class RecordDto(
    val uid: String = "",
    val animal: Animal = Animal.DOG,
    val sex: Sex = Sex.MALE,
    val name: String = "",
    val age: Int = -1,
    val isDeleted: Boolean = false,
    val imageUrl: MutableList<String> = mutableListOf()
) : Serializable