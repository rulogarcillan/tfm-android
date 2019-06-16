package tuppersoft.com.burguerfinder.features.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import tuppersoft.com.burguerfinder.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.burguerfinder.core.extension.log
import tuppersoft.com.burguerfinder.core.extension.viewModel
import tuppersoft.com.burguerfinder.core.platform.GlobalActivity
import javax.inject.Inject


class MainActivity : GlobalActivity() {

    companion object {
        const val GOOGLE_SING = 1
    }


    private lateinit var mainViewModel: MainViewModel
    private lateinit var auth: FirebaseAuth


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(tuppersoft.com.burguerfinder.R.layout.activity_main)

        initViewModel()

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        val signInButton = findViewById<SignInButton>(tuppersoft.com.burguerfinder.R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener {

            googleSignIn()
        }
    }

    private fun initViewModel() {
        mainViewModel = viewModel(viewModelFactory) { }
    }

    private fun googleSignIn() {
        val signInIntent = mainViewModel.mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SING)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SING) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignIn(task)
        }
    }


    private fun handleGoogleSignIn(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account!!)

        } catch (e: ApiException) {
            "signInResult:failed code=" + e.statusCode.toString().log()

            /* if (e.statusCode != GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
                 handleFailure(LoginFailure.UnauthorizedError)
             }*/
        }
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Authentication Ok.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    // updateUI(null)
                }
            }
    }
}
