package tuppersoft.com.adoptme.core.platform

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import tuppersoft.com.adoptme.App
import tuppersoft.com.adoptme.core.di.AppComponent

abstract class GlobalActivity : AppCompatActivity() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as App).appComponent
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            else -> return true
        }
        return true
    }
}

