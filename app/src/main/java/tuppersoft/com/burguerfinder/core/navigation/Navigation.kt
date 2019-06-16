package tuppersoft.com.burguerfinder.core.navigation

import android.content.Context
import android.content.Intent
import tuppersoft.com.burguerfinder.features.main.MainActivity

object Navigation {

    fun goMainActivity(mContext: Context) {
        val intent = Intent(mContext, MainActivity::class.java)
        mContext.startActivity(intent)
    }
}
