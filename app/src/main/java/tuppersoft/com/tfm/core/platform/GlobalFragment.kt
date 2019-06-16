package tuppersoft.com.tfm.core.platform


/**
 * Created by Raúl Rodríguez Concepción on 2019-06-16.
 * Talento Mobile
 * raulrcs@gmail.com
 */

import androidx.fragment.app.Fragment
import tuppersoft.com.tfm.App
import tuppersoft.com.tfm.core.di.AppComponent


abstract class GlobalFragment : Fragment() {
    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity().application as App).appComponent
    }
}