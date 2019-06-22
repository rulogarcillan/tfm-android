package tuppersoft.com.adoptme.features.splash

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.navigation.Navigation


class SplashActivity : AppCompatActivity() {

    companion object {
        const val TIME_SPLASH = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.secondaryColor)
        }


        YoYo.with(Techniques.FadeInDown)
            .duration(1000)
            .onEnd { startApp() }
            .playOn(findViewById(R.id.idLogoSplash))
    }

    private fun startApp() {
        Handler().postDelayed({
            Navigation.goLoginActivity(this)
        }, TIME_SPLASH)
    }

}
