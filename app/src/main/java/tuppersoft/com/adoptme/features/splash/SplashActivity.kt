package tuppersoft.com.adoptme.features.splash

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.navigation.Navigation


class SplashActivity : AppCompatActivity() {

    companion object {
        const val TIME_SPLASH = 500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }

        YoYo.with(Techniques.FadeInDown)
            .duration(500)
            .onEnd { startApp() }
            .playOn(findViewById(R.id.idLogoSplash))
    }

    private fun startApp() {
        Handler().postDelayed({
            Navigation.goLoginActivity(this)
        }, TIME_SPLASH)
    }

}
