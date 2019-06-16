package tuppersoft.com.burguerfinder.core.extension

import android.util.Log
import tuppersoft.com.burguerfinder.BuildConfig
import tuppersoft.com.burguerfinder.core.platform.TAG

fun String?.log(tag: String = TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this ?: "************** - Null value - **************")
    }
}