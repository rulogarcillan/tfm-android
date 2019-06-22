package tuppersoft.com.burguerfinder.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.burguerfinder.App
import tuppersoft.com.burguerfinder.core.di.AppComponent


abstract class GlobalActivity : AppCompatActivity() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as App).appComponent
    }

}

