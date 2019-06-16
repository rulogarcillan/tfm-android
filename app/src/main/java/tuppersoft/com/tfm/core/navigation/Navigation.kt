package tuppersoft.com.tfm.core.navigation

import android.content.Context
import android.content.Intent
import tuppersoft.com.tfm.features.main.MainActivity

object Navigation {

    fun goMainActivity(mContext: Context) {
        val intent = Intent(mContext, MainActivity::class.java)
        mContext.startActivity(intent)
    }
}
