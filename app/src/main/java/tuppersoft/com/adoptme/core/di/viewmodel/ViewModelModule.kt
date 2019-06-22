package tuppersoft.com.adoptme.core.di.viewmodel


import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    /*   @Binds
       @IntoMap
       @ViewModelKey(LoginViewModel::class)
       abstract fun bindMainViewModel(viewModel: LoginViewModel): ViewModel*/

}


