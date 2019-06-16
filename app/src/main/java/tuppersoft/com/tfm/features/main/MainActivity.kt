package tuppersoft.com.tfm.features.main

import android.os.Bundle
import tuppersoft.com.tfm.R
import tuppersoft.com.tfm.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.tfm.core.extension.viewModel
import tuppersoft.com.tfm.core.platform.GlobalActivity
import tuppersoft.com.domain.usescases.GetCharacter
import javax.inject.Inject

class MainActivity : GlobalActivity() {


    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
    }

    private fun initViewModel() {
        mainViewModel = viewModel(viewModelFactory) {

        }
    }
}
