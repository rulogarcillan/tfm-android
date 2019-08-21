package tuppersoft.com.data.repositories

import arrow.core.None
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import tuppersoft.com.data.entities.Record
import tuppersoft.com.data.mappers.toMessage
import tuppersoft.com.data.mappers.toRecord
import tuppersoft.com.data.mappers.toRecordDto
import tuppersoft.com.data.mappers.toUser
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.MessageDto
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.repositories.FirebaseRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseRepositoryImpl @Inject constructor(val auth: FirebaseAuth, private val db: FirebaseFirestore) :
    FirebaseRepository {

    override fun getUserLogin(): UserDto? {
        return auth.currentUser?.toUser()
    }

    override fun deleteRecord(record: RecordDto) {
        val docRef = db.collection("records").document(record.id)
        docRef.update("deleted", true)
    }

    override fun saveRecord(record: RecordDto): RecordDto {
        db.collection("records").document(record.id).set(record.toRecord())
        return record
    }

    override fun saveUserDataBase(user: UserDto): UserDto {
        db.collection("users").document(user.uid).set(user)
            .addOnSuccessListener { }
            .addOnFailureListener { }

        return user
    }

    override fun saveChatMessage(msg: MessageDto, chatId: String): None {
        val map = HashMap<String, String>()
        map["dummy"] = "dummy"
        db.collection("chats").document(chatId).set(map)
        db.collection("chats").document(chatId).collection(chatId).add(msg.toMessage())

        return None
    }

    override suspend fun getAllRecords(animal: Animal?): List<RecordDto> {

        return if (animal == null) {
            suspendCoroutine { continuation ->
                db.collection("records").whereEqualTo("deleted", false)
                    .get().addOnSuccessListener { items ->
                        continuation.resume(items.toObjects(Record::class.java).map { it.toRecordDto() })
                    }
            }
        } else {
            suspendCoroutine { continuation ->
                db.collection("records").whereEqualTo("animal", animal.name).whereEqualTo("deleted", false).get()
                    .addOnSuccessListener { items ->
                        continuation.resume(items.toObjects(Record::class.java).map { it.toRecordDto() })
                    }
            }
        }
    }

    override suspend fun getUserChatsDataBase(userId: String): MutableList<String> =
        suspendCoroutine { continuation ->
            db.collection("chats").get()
                .addOnSuccessListener { querySnapshot ->
                    val listUser: MutableList<String> = mutableListOf()

                    querySnapshot.documents.filter { it.id.contains(userId) }.forEach { filt ->
                        listUser.add(filt.id)
                    }

                    continuation.resume(listUser)
                }
                .addOnFailureListener { continuation.resume(mutableListOf()) }
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