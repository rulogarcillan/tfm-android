package tuppersoft.com.data.mappers

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import tuppersoft.com.data.entities.Record
import tuppersoft.com.domain.entities.RecordDto

fun getReference(uid: String): DocumentReference {
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance();
    return firestore.collection("users").document(uid)
}

fun RecordDto.toRecord() = Record(
    id,
    getReference(uid),
    animal,
    sex,
    name,
    age,
    isDeleted,
    imageUrl
)

fun Record.toRecordDto() = RecordDto(
    id,
    uid?.id.toString(),
    animal,
    sex,
    name,
    age,
    isDeleted,
    imageUrl
)