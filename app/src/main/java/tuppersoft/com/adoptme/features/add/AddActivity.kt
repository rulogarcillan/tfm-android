package tuppersoft.com.adoptme.features.add

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.view_toolbar.*
import tuppersoft.com.adoptme.core.platform.GlobalActivity


class AddActivity : GlobalActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(tuppersoft.com.adoptme.R.layout.activity_add)


        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBar?.setDisplayShowHomeEnabled(true)
        mActionBar?.title = getString(tuppersoft.com.adoptme.R.string.add)

        customRadio.setOnClick {
            Toast.makeText(this@AddActivity, it.toString(), Toast.LENGTH_LONG).show()
        }

        customRadioSex.setOnClick { }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(tuppersoft.com.adoptme.R.menu.save, menu)
        return true
    }
}
