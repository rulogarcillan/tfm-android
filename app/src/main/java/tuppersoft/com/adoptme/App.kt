package tuppersoft.com.adoptme

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import tuppersoft.com.adoptme.core.di.AppComponent
import tuppersoft.com.adoptme.core.di.AppModule
import tuppersoft.com.adoptme.core.di.DaggerAppComponent


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

