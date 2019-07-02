package tuppersoft.com.adoptme.features.personaldata

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_personal_data.*
import kotlinx.android.synthetic.main.view_toolbar.*
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.platform.GlobalActivity
import tuppersoft.com.data.repositories.SharedPreferencesRepository
import tuppersoft.com.domain.entities.UserDto
import javax.inject.Inject


class PersonalDataActivity : GlobalActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var personalDataViewModel: PersonalDataViewModel
    lateinit var user: UserDto


    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(tuppersoft.com.adoptme.R.layout.activity_personal_data)
        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBar?.setDisplayShowHomeEnabled(true)
        mActionBar?.setDisplayShowTitleEnabled(true)
        mActionBar?.title = getString(tuppersoft.com.adoptme.R.string.personal_data_title)
        user = SharedPreferencesRepository.loadPreferenceObject(this, "USER", UserDto()) as UserDto
        setData()
        initViewModel()
        initEditfields()
    }

    private fun initViewModel() {
        personalDataViewModel = viewModel(viewModelFactory) {
            observe(userDto, ::handleSaveUser)
        }
    }

    fun handleSaveUser(userDto: UserDto) {
        user = userDto
        SharedPreferencesRepository.savePreferenceObject(this, "USER", user)
    }

    private fun setData() {
        tvName.text = user.name
        tvEmail.text = user.email
        etPostalCode.setText(user.zip)
    }

    private fun initEditfields() {
        ivEditPostalCode.setOnClickListener {
            etPostalCode.isEnabled = true
            etPostalCode.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(etPostalCode, InputMethodManager.SHOW_IMPLICIT)
            user.zip = etPostalCode.text.toString()
            personalDataViewModel.saveUSer(user)
        }

    }


    /*   override fun onConfigurationChanged(newConfig: Configuration) {
           super.onConfigurationChanged(newConfig)

           // Checks whether a hardware keyboard is available
           if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
               Toast.makeText(this, "keyboard visible", Toast.LENGTH_SHORT).show()
           } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
               Toast.makeText(this, "keyboard hidden", Toast.LENGTH_SHORT).show()
           }
       }*/
}
