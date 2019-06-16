package tuppersoft.com.tfm.core.extension

import android.widget.ImageView
import tuppersoft.com.tfm.R
import tuppersoft.com.tfm.core.platform.GlideApp


/**
 * Created by Raúl Rodríguez Concepción on 2019-06-16.
 * Talento Mobile
 * raulrcs@gmail.com
 */

fun ImageView.loadFromUrl(url: String?, width: Int? = null, height: Int? = null) {
    if (width != null && height != null) {
        GlideApp.with(context).load(url).placeholder(R.drawable.ic_img_placeholder).override(width, height).into(this)
    } else {
        GlideApp.with(context).load(url).placeholder(R.drawable.ic_img_placeholder).into(this)
    }
}