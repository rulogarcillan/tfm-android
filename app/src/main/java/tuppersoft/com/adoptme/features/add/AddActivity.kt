package tuppersoft.com.adoptme.features.add

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.view_toolbar.*
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.R.*
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.platform.GlobalActivity
import tuppersoft.com.domain.entities.Animal
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.Sex
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AddActivity : GlobalActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var addViewModel: AddViewModel
    private lateinit var mCurrentPhotoPath: String
    private lateinit var itemSaveMenu: MenuItem

    companion object {
        private const val REQUEST_IMAGE_CAPTURE_1 = 1
        private const val REQUEST_IMAGE_CAPTURE_2 = 2
        private const val REQUEST_IMAGE_CAPTURE_3 = 3
        private const val REQUEST_IMAGE_CAPTURE_4 = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_add)

        initViewModel()
        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBar?.setDisplayShowHomeEnabled(true)
        mActionBar?.title = getString(string.add)



        card1.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_1) }
        card2.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_2) }
        card3.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_3) }
        card4.setOnClickListener { dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE_4) }

        crAnimal.setOnClick {
            when (it) {
                1 -> addViewModel.addAnimal(Animal.DOG)
                2 -> addViewModel.addAnimal(Animal.CAT)
                3 -> addViewModel.addAnimal(Animal.RABBIT)
                4 -> addViewModel.addAnimal(Animal.OTHER)
            }
        }
        crSex.setOnClick {
            when (it) {
                1 -> addViewModel.addSex(Sex.MALE)
                2 -> addViewModel.addSex(Sex.FEMALE)
            }
        }

        tiName.doAfterTextChanged { addViewModel.addName(it?.toString() ?: "") }
        tiAge.doAfterTextChanged { addViewModel.addAge(it?.toString()?.toInt() ?: -1) }
    }

    private fun initViewModel() {
        addViewModel = viewModel(viewModelFactory) {
            observe(urlPhoto1, ::handleEndPhoto1)
            observe(urlPhoto2, ::handleEndPhoto2)
            observe(urlPhoto3, ::handleEndPhoto3)
            observe(urlPhoto4, ::handleEndPhoto4)
            observe(recordDto, ::handleRecord)
        }
    }

    fun handleRecord(record: RecordDto) {
        if (record.imageUrl.size > 0 && record.age >= 0 && record.name != "") {
            Toast.makeText(this, "Se puede", Toast.LENGTH_SHORT).show()
            if (this::itemSaveMenu.isInitialized) {
                itemSaveMenu.isVisible = true
            }
        } else {
            if (this::itemSaveMenu.isInitialized) {
                itemSaveMenu.isVisible = false
            }
        }
    }

    fun handleEndPhoto1(photo1: String) {
        sync1.visibility = View.GONE
        ivSync1.clearAnimation()
    }

    fun handleEndPhoto2(photo1: String) {
        sync2.visibility = View.GONE
        ivSync2.clearAnimation()
    }

    fun handleEndPhoto3(photo1: String) {
        sync3.visibility = View.GONE
        ivSync3.clearAnimation()
    }

    fun handleEndPhoto4(photo1: String) {
        sync4.visibility = View.GONE
        ivSync4.clearAnimation()
    }

    private fun handleStartPhoto(who: Int, file: File) {
        val rotation = AnimationUtils.loadAnimation(this, anim.rotate)
        rotation.fillAfter = true
        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri.fromFile(file))
        when (who) {
            REQUEST_IMAGE_CAPTURE_1 -> {
                ivSync1.startAnimation(rotation)
                image1.setImageBitmap(bitmap)
                sync1.visibility = View.VISIBLE
                addViewModel.uploadPhoto1(file)
            }
            REQUEST_IMAGE_CAPTURE_2 -> {
                ivSync2.startAnimation(rotation)
                image2.setImageBitmap(bitmap)
                sync2.visibility = View.VISIBLE
                addViewModel.uploadPhoto2(file)
            }
            REQUEST_IMAGE_CAPTURE_3 -> {
                ivSync3.startAnimation(rotation)
                image3.setImageBitmap(bitmap)
                sync3.visibility = View.VISIBLE
                addViewModel.uploadPhoto3(file)
            }
            REQUEST_IMAGE_CAPTURE_4 -> {
                ivSync4.startAnimation(rotation)
                image4.setImageBitmap(bitmap)
                sync4.visibility = View.VISIBLE
                addViewModel.uploadPhoto4(file)
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.save, menu)
        itemSaveMenu = menu.getItem(0)
        itemSaveMenu.isVisible = false
        return true
    }

    private fun dispatchTakePictureIntent(who: Int) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (takePictureIntent.resolveActivity(packageManager) != null) {

            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
            }

            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(
                    this,
                    "tuppersoft.com.adoptme.fileprofider",
                    photoFile
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, who)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_IMAGE_CAPTURE_1 || requestCode == REQUEST_IMAGE_CAPTURE_2 || requestCode == REQUEST_IMAGE_CAPTURE_3 || requestCode == REQUEST_IMAGE_CAPTURE_4) && resultCode == RESULT_OK) {
            val file = File(mCurrentPhotoPath)
            handleStartPhoto(requestCode, file)
        }
    }

    /* @Throws(IOException::class)
     private fun Bitmap.getFileFromBitmap(mContext: Context): File {
         val f = File(mContext.cacheDir, "tempFileName")
         f.createNewFile()
         val bos = ByteArrayOutputStream()
         this.compress(Bitmap.CompressFormat.JPEG, 100, bos)
         val toByteArray = bos.toByteArray()
         val fos = FileOutputStream(f)
         fos.write(toByteArray)
         fos.flush()
         fos.close()
         return f
     }*/


    @Throws(IOException::class)
    private fun createImageFile(): File {

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ROOT).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            "TEMP_FILE_PHOTO",
            ".jpg",
            storageDir
        )
        mCurrentPhotoPath = image.absolutePath
        return image
    }

}
