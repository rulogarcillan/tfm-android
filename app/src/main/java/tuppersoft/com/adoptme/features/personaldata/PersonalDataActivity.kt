package tuppersoft.com.adoptme.features.personaldata

import android.os.Bundle
import kotlinx.android.synthetic.main.view_toolbar.*
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.platform.GlobalActivity


class PersonalDataActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)


        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBar?.setDisplayShowHomeEnabled(true)
        mActionBar?.setDisplayShowTitleEnabled(true)
        mActionBar?.title = getString(R.string.personal_data_title)
    }
}
