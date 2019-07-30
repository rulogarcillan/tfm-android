package tuppersoft.com.data.entities

import com.google.firebase.firestore.DocumentReference
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.Sex

data class Record(
    val uid: DocumentReference,
    val animal: Animal = Animal.DOG,
    val sex: Sex = Sex.MALE,
    val name: String = "",
    val age: Int = -1,
    val imageUrl: MutableList<String> = mutableListOf()
)