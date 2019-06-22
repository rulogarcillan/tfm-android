package tuppersoft.com.adoptme.features.login

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import tuppersoft.com.adoptme.core.platform.GlobalContextViewModel
import tuppersoft.com.adoptme.core.platform.GlobalFunctions
import javax.inject.Inject

class LoginViewModel @Inject constructor(val app: Application) : GlobalContextViewModel(app) {

    var mGoogleSignInClient: GoogleSignInClient = GlobalFunctions.getGoogleSignInClient(app)

}