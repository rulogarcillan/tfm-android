package tuppersoft.com.adoptme.features.chat

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import tuppersoft.com.adoptme.core.extension.log
import tuppersoft.com.adoptme.core.platform.GlobalViewModel
import tuppersoft.com.data.entities.Message
import tuppersoft.com.data.mappers.toMessageDto
import tuppersoft.com.domain.entities.MessageDto
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.GetUser
import tuppersoft.com.domain.usescases.SendMessage
import tuppersoft.com.domain.usescases.SendMessage.Params
import javax.inject.Inject

/**
 * Created by Raúl Rodríguez Concepción on 2019-08-21.
 *
 * raulrcs@gmail.com
 */

class ChatViewModel @Inject constructor(
    private val getUser: GetUser,
    private val sendMessage: SendMessage,
    private val db: FirebaseFirestore
) : GlobalViewModel() {

    val msgList: MutableList<MessageDto> = mutableListOf()
    val user: MutableLiveData<UserDto> = MutableLiveData()

    val msgListLive: MutableLiveData<MutableList<MessageDto>> = MutableLiveData()

    fun initChat(chatId: String) {
        val docRef = db.collection("chats").document(chatId).collection(chatId).get()

        docRef.addOnSuccessListener { it ->

            it.query.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    "Listen failed".log()
                    return@addSnapshotListener
                }
                if (snapshot != null && !snapshot.metadata.hasPendingWrites()) {
                    msgList.clear()
                    msgList.addAll(snapshot.toObjects(Message::class.java).map { it.toMessageDto() }.toMutableList().sortedBy { it.timeStamp })
                    msgListLive.value = msgList
                }
            }

        }
    }

    fun getUser(userId: String) {
        getUser.invoke(GetUser.Params(userId)) {
            user.value = it
        }
    }

    fun sendMessage(msg: MessageDto, chatId: String) {
        sendMessage.invoke(Params(msg, chatId))
    }
}