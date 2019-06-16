package tuppersoft.com.tfm

import android.app.Application
import tuppersoft.com.tfm.core.di.AppComponent
import tuppersoft.com.tfm.core.di.AppModule
import tuppersoft.com.tfm.core.di.DaggerAppComponent


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
    }

    private fun injectMembers() = appComponent.inject(this)
}

