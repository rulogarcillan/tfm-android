package tuppersoft.com.adoptme.features.messages

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.extension.loadFromUrl
import tuppersoft.com.domain.entities.UserDto

class ChatsViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    private val ivAvatar = rootView.findViewById<ImageView>(R.id.ivAvatar)
    private val tvName = rootView.findViewById<TextView>(R.id.tvName)
    private val clRoot = rootView.findViewById<ConstraintLayout>(R.id.clRoot)
    fun bind(userDto: UserDto, uid: (String) -> Unit) {

        ivAvatar.loadFromUrl(userDto.photoUrl)
        tvName.text = userDto.name

        clRoot.setOnClickListener {
            uid(userDto.uid)
        }
    }
}