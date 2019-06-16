package tuppersoft.com.burguerfinder

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import tuppersoft.com.burguerfinder.core.di.AppComponent
import tuppersoft.com.burguerfinder.core.di.AppModule
import tuppersoft.com.burguerfinder.core.di.DaggerAppComponent


class App : Application() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        injectMembers()
        FirebaseAnalytics.getInstance(this)
    }

    private fun injectMembers() = appComponent.inject(this)
}

