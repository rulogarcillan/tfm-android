package tuppersoft.com.burguerfinder.features.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import tuppersoft.com.burguerfinder.R
import tuppersoft.com.burguerfinder.core.navigation.Navigation


class SplashActivity : AppCompatActivity() {

    companion object {
        const val TIME_SPLASH = 3000L
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        YoYo.with(Techniques.FadeInDown)
            .duration(1500)
            .onEnd { startApp() }
            .playOn(findViewById(R.id.animation_view))


    }

    private fun startApp() {
        Handler().postDelayed({
            Navigation.goMainActivity(this)
            finish()
        }, TIME_SPLASH)

    }

}
