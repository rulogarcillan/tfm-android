package tuppersoft.com.adoptme.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tuppersoft.com.domain.exception.Failure

abstract class GlobalViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}