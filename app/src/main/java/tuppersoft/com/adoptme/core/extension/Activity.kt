package tuppersoft.com.adoptme.core.extension

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}


fun Activity.finishOrNot(finish: Boolean) {
    if (finish) {
        this.finish()
    }
}

