package tuppersoft.com.adoptme.core.platform

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import tuppersoft.com.adoptme.R


/**
 * Created by Raúl Rodríguez Concepción on 2019-06-16.
 * Talento Mobile
 * raulrcs@gmail.com
 */
object GlobalFunctions {


    fun getGoogleSignInClient(app: Application): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(app.getString(R.string.client_id))
            .build()
        return GoogleSignIn.getClient(app, gso)
    }

}