package tuppersoft.com.adoptme.features.chat

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.adoptme.core.extension.loadFromUrl
import tuppersoft.com.domain.entities.MessageDto
import tuppersoft.com.domain.entities.UserDto
import java.text.SimpleDateFormat

class ChatReceivedViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    private val tvMsg = rootView.findViewById<TextView>(tuppersoft.com.adoptme.R.id.tvMsg)
    private val tvDate = rootView.findViewById<TextView>(tuppersoft.com.adoptme.R.id.tvDate)
    private val tvName = rootView.findViewById<TextView>(tuppersoft.com.adoptme.R.id.tvName)
    private val ivAvatar = rootView.findViewById<ImageView>(tuppersoft.com.adoptme.R.id.ivAvatar)

    fun bind(messageDto: MessageDto, otherUser: UserDto) {
        tvMsg.text = messageDto.msg
        ivAvatar.loadFromUrl(otherUser.photoUrl)
        tvName.text = otherUser.name
        messageDto.timeStamp?.let {
            tvDate.text = SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(it)
        }
    }
}