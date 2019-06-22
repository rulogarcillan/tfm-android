package tuppersoft.com.adoptme.core.platform

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tuppersoft.com.domain.exception.Failure


abstract class GlobalContextViewModel(app: Application) : AndroidViewModel(app) {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}