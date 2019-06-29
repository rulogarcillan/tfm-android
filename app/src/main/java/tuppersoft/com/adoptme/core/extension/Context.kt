package tuppersoft.com.adoptme.core.extension

import android.content.Context
import java.io.File
import java.text.DateFormat

fun Context.getAppTimeStamp(): String {
    var timeStamp = ""
    try {
        val appInfo = this.packageManager.getApplicationInfo(this.packageName, 0)
        val appFile = appInfo.sourceDir
        val time = File(appFile).lastModified()
        val formatter = DateFormat.getDateTimeInstance()
        timeStamp = formatter.format(time)
    } catch (e: Exception) {
    }

    return timeStamp
}