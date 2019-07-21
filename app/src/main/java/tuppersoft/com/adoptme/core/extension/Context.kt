package tuppersoft.com.adoptme.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
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

val Context.networkInfo: NetworkInfo? get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

//context.networkInfo?.isConnectedOrConnecting