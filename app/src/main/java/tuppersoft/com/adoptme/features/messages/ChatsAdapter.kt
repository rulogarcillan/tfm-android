package tuppersoft.com.adoptme.features.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.adoptme.R
import tuppersoft.com.domain.entities.UserDto

class ChatsAdapter(var list: MutableList<UserDto>, val uid: (String) -> Unit) :
    RecyclerView.Adapter<ChatsViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {

        return ChatsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_chat,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        holder.bind(list[position], uid)
    }
}