package tuppersoft.com.adoptme.features.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.idNavigationView
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalActivity

class MainActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Navigation.goHomeFragment(supportFragmentManager)
        initNavigation()
    }

    private fun initNavigation() {

        idNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> Navigation.goHomeFragment(supportFragmentManager)
              //  R.id.navigation_discovery_menu -> Navigation.goDiscoveryFragment(supportFragmentManager)
                R.id.navigation_bookmark -> Navigation.goBookmarksFragment(supportFragmentManager)
                R.id.navigation_messages -> Navigation.goMessagesFragment(supportFragmentManager)
                R.id.navigation_profile -> Navigation.goProfileFragment(supportFragmentManager)
            }
            true
        }
    }
}
