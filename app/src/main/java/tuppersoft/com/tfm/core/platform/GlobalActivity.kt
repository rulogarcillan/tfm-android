package tuppersoft.com.tfm.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.tfm.App
import tuppersoft.com.tfm.core.di.AppComponent


abstract class GlobalActivity : AppCompatActivity() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as App).appComponent
    }

    //region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //endregion

}

