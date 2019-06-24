package tuppersoft.com.adoptme.core.navigation

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.extension.finishOrNot
import tuppersoft.com.adoptme.features.login.LoginActivity
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.adoptme.features.profile.ProfileFragment


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

    fun goProfileFragment(manager: FragmentManager) {
        val profileFragment = ProfileFragment.newInstance()
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.idFrameLayout, profileFragment, ProfileFragment::class.java.name)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}
