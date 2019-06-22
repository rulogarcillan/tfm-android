package tuppersoft.com.adoptme.core.platform


/**
 * Created by Raúl Rodríguez Concepción on 2019-06-16.
 * Talento Mobile
 * raulrcs@gmail.com
 */

import androidx.fragment.app.Fragment
import tuppersoft.com.adoptme.App
import tuppersoft.com.adoptme.core.di.AppComponent


abstract class GlobalFragment : Fragment() {
    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity().application as App).appComponent
    }
}