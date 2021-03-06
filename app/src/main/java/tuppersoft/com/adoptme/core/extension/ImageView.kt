package tuppersoft.com.adoptme.core.extension

import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.platform.GlideApp

/**
 * Created by Raúl Rodríguez Concepción on 2019-06-16.
 *
 * raulrcs@gmail.com
 */

fun ImageView.loadFromUrl(url: String?, width: Int? = null, height: Int? = null) {
    if (width != null && height != null) {
        GlideApp.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_img_placeholder).override(width, height).into(this)
    } else {
        GlideApp.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_img_placeholder).into(this)
    }
}