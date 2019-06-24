package tuppersoft.com.adoptme.features.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalActivity


class MainActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {

        idNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> ""
                R.id.navigation_discovery_menu -> ""
                R.id.navigation_bookbark -> ""
                R.id.navigation_top -> ""
                R.id.navigation_profile -> Navigation.goProfileFragment(supportFragmentManager)
            }
            true
        }
    }
}
