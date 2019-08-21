package tuppersoft.com.adoptme.features.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import tuppersoft.com.adoptme.R
import tuppersoft.com.domain.entities.MessageDto
import tuppersoft.com.domain.entities.UserDto

class ChatAdapter(var items: MutableList<MessageDto>, private val myUid: String, var otherUser: UserDto) :
    RecyclerView.Adapter<ViewHolder>() {


    companion object {
        const val TYPE_RECEIVED = 1
        const val TYPE_SEND = 2
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position].uid == myUid) {
            TYPE_SEND
        } else {
            TYPE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        when (viewType) {
            TYPE_SEND -> {

                return ChatSendViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_send,
                        parent,
                        false
                    )
                )
            }
            TYPE_RECEIVED -> {

                return ChatReceivedViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_received,
                        parent,
                        false
                    )
                )
            }
            else -> return ChatSendViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_send,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ChatSendViewHolder) {
            holder.bind(items[position])
        } else if (holder is ChatReceivedViewHolder) {
            holder.bind(items[position], otherUser)
        }
    }
}