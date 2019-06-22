package tuppersoft.com.adoptme.features.main

import android.os.Bundle
import tuppersoft.com.adoptme.core.platform.GlobalActivity


class MainActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(tuppersoft.com.adoptme.R.layout.activity_main)
    }

}
