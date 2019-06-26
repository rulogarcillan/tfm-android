package tuppersoft.com.adoptme.features.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.platform.GlobalAdapter

class ProfileAdapter(list: MutableList<ProfileItemMenu>, private val globalActions: (ProfileItemMenu) -> Unit) : GlobalAdapter<ProfileItemMenu>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProfileHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_menu_profile,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProfileHolder).bind(getItem(position), globalActions)
    }
}