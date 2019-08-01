package tuppersoft.com.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import tuppersoft.com.data.entities.Record
import tuppersoft.com.data.mappers.toRecord
import tuppersoft.com.data.mappers.toRecordDto
import tuppersoft.com.data.mappers.toUser
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRepositoryImpl @Inject constructor(val auth: FirebaseAuth, private val db: FirebaseFirestore) : FirebaseRepository {

    override fun getUserLogin(): UserDto? {
        return auth.currentUser?.toUser()
    }

    override fun saveRecord(record: RecordDto): RecordDto {

        db.collection("records").add(record.toRecord())
            .addOnSuccessListener {
                it.toString()
            }
            .addOnFailureListener {
                it.toString()
            }
        return record
    }

    override fun saveUserDataBase(user: UserDto): UserDto {
        db.collection("users").document(user.uid).set(user)
            .addOnSuccessListener { }
            .addOnFailureListener { }

        return user
    }

    override suspend fun getAllRecords(animal: Animal?): List<RecordDto> {

        return if (animal == null) {
            suspendCoroutine { continuation ->
                db.collection("records").get().addOnSuccessListener { items ->
                    continuation.resume(items.toObjects(Record::class.java).map { it.toRecordDto() })
                }
            }
        } else {
            suspendCoroutine { continuation ->
                db.collection("records").whereEqualTo("animal", animal.name).get().addOnSuccessListener { items ->
                    continuation.resume(items.toObjects(Record::class.java).map { it.toRecordDto() })
                }
            }
        }
    }

    override suspend fun getUserDataBase(userId: String): UserDto? =
        suspendCoroutine { continuation ->
            db.collection("users").document(userId).get()
                .addOnSuccessListener {
                    continuation.resume(it.toObject(UserDto::class.java))
                }
                .addOnFailureListener { continuation.resume(null) }
        }

    override suspend fun loginWithFirebase(token: String): UserDto? =
        suspendCoroutine { continuation ->
            val credential = GoogleAuthProvider.getCredential(token, null)
            auth.signInWithCredential(credential).addOnCompleteListener({ it.run() }, { task ->
                if (task.isSuccessful) {
                    continuation.resume(task.result?.user?.toUser())
                } else {
                }
            })
        }
}