package tuppersoft.com.burguerfinder.features.main

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import tuppersoft.com.burguerfinder.core.platform.GlobalContextViewModel
import tuppersoft.com.burguerfinder.core.platform.GlobalFunctions
import javax.inject.Inject

class MainViewModel @Inject constructor(val app: Application) : GlobalContextViewModel(app) {

    var mGoogleSignInClient: GoogleSignInClient = GlobalFunctions.getGoogleSignInClient(app)


}