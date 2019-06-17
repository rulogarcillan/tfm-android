package tuppersoft.com.burguerfinder.core.navigation

import android.app.Activity
import android.content.Intent
import tuppersoft.com.burguerfinder.core.extension.finishOrNot
import tuppersoft.com.burguerfinder.features.login.LoginActivity
import tuppersoft.com.burguerfinder.features.main.MainActivity

object Navigation {
    fun goLoginActivity(mActivity: Activity, finish: Boolean = true) {
        val intent = Intent(mActivity, LoginActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }

    fun goMainActivity(mActivity: Activity, finish: Boolean = true) {
        val intent = Intent(mActivity, MainActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }
}
