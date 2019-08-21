package tuppersoft.com.adoptme.features.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.adoptme.R
import tuppersoft.com.domain.entities.MessageDto
import java.text.SimpleDateFormat

class ChatSendViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    private val tvMsg = rootView.findViewById<TextView>(R.id.tvMsg)
    private val tvDate = rootView.findViewById<TextView>(R.id.tvDate)

    fun bind(messageDto: MessageDto) {
        tvMsg.text = messageDto.msg
        messageDto.timeStamp?.let {
            tvDate.text = SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(it)
        }
    }
}