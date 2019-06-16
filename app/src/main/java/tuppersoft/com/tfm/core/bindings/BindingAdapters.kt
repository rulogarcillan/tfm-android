package tuppersoft.com.tfm.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import tuppersoft.com.tfm.core.extension.loadFromUrl

@BindingAdapter("avatar")
fun avatar(view: ImageView, url: String?) {
    view.loadFromUrl(url)
}