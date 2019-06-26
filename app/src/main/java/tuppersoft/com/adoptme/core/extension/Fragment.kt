package tuppersoft.com.adoptme.core.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


inline fun <reified T : ViewModel> Fragment.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

inline fun <reified T : ViewModel> Fragment.viewModel(
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this)[T::class.java]
    vm.body()
    return vm
}