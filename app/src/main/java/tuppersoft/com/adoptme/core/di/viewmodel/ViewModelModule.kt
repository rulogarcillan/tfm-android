package tuppersoft.com.adoptme.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tuppersoft.com.adoptme.features.add.AddViewModel
import tuppersoft.com.adoptme.features.animalProfile.AnimalsProfileViewModel
import tuppersoft.com.adoptme.features.chat.ChatViewModel
import tuppersoft.com.adoptme.features.home.HomeViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimalsProfileViewModel::class)
    abstract fun bindAnimalsProfileViewModel(viewModel: AnimalsProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindChatViewModel(viewModel: ChatViewModel): ViewModel
}


