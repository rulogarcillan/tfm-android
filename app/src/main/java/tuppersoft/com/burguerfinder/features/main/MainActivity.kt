package tuppersoft.com.burguerfinder.features.main

import android.os.Bundle
import tuppersoft.com.burguerfinder.R
import tuppersoft.com.burguerfinder.core.platform.GlobalActivity


class MainActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
