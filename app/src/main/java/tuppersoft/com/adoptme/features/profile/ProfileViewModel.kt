package tuppersoft.com.adoptme.features.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import tuppersoft.com.adoptme.core.platform.GlobalContextViewModel
import tuppersoft.com.adoptme.core.platform.GlobalFunctions

class ProfileViewModel constructor(private val app: Application) : GlobalContextViewModel(app) {

    var logoutState: MutableLiveData<Boolean> = MutableLiveData()
    private val mGoogleSignInClient: GoogleSignInClient by lazy { GlobalFunctions.getGoogleSignInClient(app) }

    fun logout() {
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        mGoogleSignInClient.revokeAccess()
        logoutState.value = true
    }
}