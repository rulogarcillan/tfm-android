package tuppersoft.com.adoptme.features.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.log
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalActivity
import tuppersoft.com.adoptme.core.platform.GlobalFunctions
import tuppersoft.com.data.repositories.SharedPreferencesRepository
import tuppersoft.com.domain.entities.UserDto
import javax.inject.Inject


class LoginActivity : GlobalActivity() {

    companion object {
        const val GOOGLE_SING = 1
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var loginViewModel: LoginViewModel
    private val mGoogleSignInClient: GoogleSignInClient by lazy { GlobalFunctions.getGoogleSignInClient(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        configStatusBar()
        initViewModel()

    }

    private fun initViewModel() {
        loginViewModel = viewModel(viewModelFactory) {
            observe(userDto, ::handleLogin)
        }
    }

    fun handleLogin(userDto: UserDto?) {
        if (userDto != null) {
            Navigation.goMainActivity(this)
            SharedPreferencesRepository.savePreferenceObject(this, "USER", userDto)
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
                loginViewModel.login(account.idToken ?: "")
            }
        } catch (e: ApiException) {
            "signInResult:failed code=" + e.statusCode.toString().log()
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
