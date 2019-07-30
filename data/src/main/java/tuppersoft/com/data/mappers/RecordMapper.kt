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
    getReference(uid),
    animal,
    sex,
    name,
    age,
    imageUrl
)

fun Record.toRecordDto() = RecordDto(
    uid.id.toString(),
    animal,
    sex,
    name,
    age,
    imageUrl
)