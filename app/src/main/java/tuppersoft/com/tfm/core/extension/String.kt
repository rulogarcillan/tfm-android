package tuppersoft.com.tfm.core.extension

import android.util.Log
import tuppersoft.com.tfm.BuildConfig
import tuppersoft.com.tfm.core.platform.TAG

fun String?.log(tag: String = TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this ?: "************** - Null value - **************")
    }
}