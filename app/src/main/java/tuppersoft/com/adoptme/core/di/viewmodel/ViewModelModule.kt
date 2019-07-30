package tuppersoft.com.adoptme.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tuppersoft.com.adoptme.features.add.AddViewModel
import tuppersoft.com.adoptme.features.login.LoginViewModel
import tuppersoft.com.adoptme.features.personaldata.PersonalDataViewModel

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonalDataViewModel::class)
    abstract fun bindPersonalDataViewModel(viewModel: PersonalDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddViewModel::class)
    abstract fun bindAddViewModel(viewModel: AddViewModel): ViewModel
}


