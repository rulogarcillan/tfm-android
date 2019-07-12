package tuppersoft.com.adoptme.features.add

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.view_toolbar.*
import tuppersoft.com.adoptme.core.platform.GlobalActivity


class AddActivity : GlobalActivity() {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE_1 = 1
        private const val REQUEST_IMAGE_CAPTURE_2 = 2
        private const val REQUEST_IMAGE_CAPTURE_3 = 3
        private const val REQUEST_IMAGE_CAPTURE_4 = 4

        private const val DATA = "data"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(tuppersoft.com.adoptme.R.layout.activity_add)

        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBar?.setDisplayShowHomeEnabled(true)
        mActionBar?.title = getString(tuppersoft.com.adoptme.R.string.add)

        card1.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_1) }
        card2.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_2) }
        card3.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_3) }
        card4.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_4) }


        customRadio.setOnClick {
            Toast.makeText(this@AddActivity, it.toString(), Toast.LENGTH_LONG).show()
        }

        customRadioSex.setOnClick { }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(tuppersoft.com.adoptme.R.menu.save, menu)
        return true
    }


    private fun dispatchTakePictureIntent(who: Int) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, who)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE_1 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get(DATA) as Bitmap
            image1.setImageBitmap(imageBitmap)
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_2 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get(DATA) as Bitmap
            image2.setImageBitmap(imageBitmap)
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_3 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get(DATA) as Bitmap
            image3.setImageBitmap(imageBitmap)
        } else if (requestCode == REQUEST_IMAGE_CAPTURE_4 && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get(DATA) as Bitmap
            image4.setImageBitmap(imageBitmap)
        }
    }

}
