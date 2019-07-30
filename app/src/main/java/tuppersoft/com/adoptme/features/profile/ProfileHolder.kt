package tuppersoft.com.adoptme.features.profile

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_menu_profile.view.imIconProfileMenu
import kotlinx.android.synthetic.main.item_menu_profile.view.ivArrow
import kotlinx.android.synthetic.main.item_menu_profile.view.tvTextProfileMenu

class ProfileHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    fun bind(item: ProfileItemMenu, globalActions: (ProfileItemMenu) -> Unit) {
        rootView.imIconProfileMenu.setImageDrawable(AppCompatResources.getDrawable(rootView.context, item.imageId))
        rootView.tvTextProfileMenu.text = rootView.context.getText(item.stringId)
        when (item.showArrow) {
            true -> rootView.ivArrow.visibility = View.VISIBLE
            false -> rootView.ivArrow.visibility = View.GONE
        }

        rootView.setOnClickListener { globalActions(item) }
    }
}