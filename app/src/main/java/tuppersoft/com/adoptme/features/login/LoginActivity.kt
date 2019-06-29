package tuppersoft.com.adoptme.features.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.log
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalActivity
import tuppersoft.com.adoptme.core.platform.GlobalFunctions
import tuppersoft.com.domain.entities.UserDto
import tuppersoft.com.domain.usescases.GetUser
import javax.inject.Inject


class LoginActivity : GlobalActivity() {

    companion object {
        const val GOOGLE_SING = 1
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var loginViewModel: LoginViewModel

    private lateinit var auth: FirebaseAuth

    @Inject
    lateinit var getUser: GetUser

    private val mGoogleSignInClient: GoogleSignInClient by lazy { GlobalFunctions.getGoogleSignInClient(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        auth = FirebaseAuth.getInstance()
        initViewModel()
        configStatusBar()
    }


    private fun initViewModel() {
        loginViewModel = viewModel(viewModelFactory) {
            observe(userDto, ::handleLogin)
        }
    }

    fun handleLogin(userDto: UserDto?) {
        if (userDto != null) {
            Navigation.goMainActivity(this)
        }
    }

    fun onClickGoogle(view: View) {
        googleSignIn()
    }


    private fun googleSignIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
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
            account?.let {
                firebaseAuthWithGoogle(account)
            }

        } catch (e: ApiException) {
            "signInResult:failed code=" + e.statusCode.toString().log()
        }
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Navigation.goMainActivity(this)
                } else {
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun configStatusBar() {
        val w = window
        w.setFlags(LayoutParams.FLAG_LAYOUT_NO_LIMITS, LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = w.decorView
            decor.systemUiVisibility = 0
        }
    }
}
