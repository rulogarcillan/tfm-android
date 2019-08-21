package tuppersoft.com.data.entities

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ServerTimestamp

/**
 * Created by Raúl Rodríguez Concepción on 2019-08-21.
 *
 * raulrcs@gmail.com
 */
data class Message(
    val msg: String = "",
    val uid: DocumentReference? = null,
    @ServerTimestamp
    val timeStamp: Timestamp? = null
)
