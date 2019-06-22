package tuppersoft.com.adoptme.core.navigation

import android.app.Activity
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import tuppersoft.com.adoptme.core.extension.finishOrNot
import tuppersoft.com.adoptme.features.login.LoginActivity
import tuppersoft.com.adoptme.features.main.MainActivity

object Navigation {
    fun goLoginActivity(mActivity: Activity, finish: Boolean = true) {

        val auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            goMainActivity(mActivity)
        } else {
            val intent = Intent(mActivity, LoginActivity::class.java)
            mActivity.startActivity(intent)
            mActivity.finishOrNot(finish)
        }
    }

    fun goMainActivity(mActivity: Activity, finish: Boolean = true) {
        val intent = Intent(mActivity, MainActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }
}
