package tuppersoft.com.adoptme.core.platform

import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.adoptme.App
import tuppersoft.com.adoptme.core.di.AppComponent


abstract class GlobalActivity : AppCompatActivity() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as App).appComponent
    }

}

