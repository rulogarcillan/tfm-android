package tuppersoft.com.data.entities

import com.google.firebase.firestore.DocumentReference
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.Sex
import java.util.UUID

data class Record
    (
    val id: String = UUID.randomUUID().toString(),
    val uid: DocumentReference? = null,
    val animal: Animal = Animal.DOG,
    val sex: Sex = Sex.MALE,
    val name: String = "",
    val age: Int = -1,
    val isDeleted: Boolean = false,
    val imageUrl: MutableList<String> = mutableListOf()
)