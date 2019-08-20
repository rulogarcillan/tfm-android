package tuppersoft.com.adoptme.core.extension

import android.util.Log
import tuppersoft.com.adoptme.BuildConfig
import tuppersoft.com.adoptme.core.platform.TAG

fun String?.log(tag: String = TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, this ?: "************** - Null value - **************")
    }
}

infix fun String.sortCombine(value2: String): String {

    val list: MutableList<String> = mutableListOf()
    list.add(this)
    list.add(value2)

    var result = ""
    list.sorted().forEach {
        result += it
    }
    return result
}