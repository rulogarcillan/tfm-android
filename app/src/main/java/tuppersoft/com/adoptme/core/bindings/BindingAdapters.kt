package tuppersoft.com.adoptme.core.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import tuppersoft.com.adoptme.core.extension.loadFromUrl

@BindingAdapter("avatar")
fun avatar(view: ImageView, url: String?) {
    view.loadFromUrl(url)
}