package tuppersoft.com.adoptme.core.platform

/**
 * Created by Raúl Rodríguez Concepción on 2019-06-16.
 * Talento Mobile
 * raulrcs@gmail.com
 */

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.myToolbar
import kotlinx.android.synthetic.main.fragment_animal_profile.view.idMsg
import tuppersoft.com.adoptme.App
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.di.AppComponent
import tuppersoft.com.adoptme.features.main.MainActivity

abstract class GlobalFragment : Fragment() {

    val appComponent: AppComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (requireActivity().application as App).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configStatusBar()
    }

    private fun configStatusBar() {
        val mActivity = activity
        mActivity?.let {
            val window = mActivity.window
            window.statusBarColor = ContextCompat.getColor(mActivity, R.color.primaryColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val decor = window.decorView
                decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }

    protected open fun showToolbar(): Boolean = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            if (showToolbar()) {
                context.myToolbar?.let { it.visibility = View.VISIBLE }
            } else {
                context.myToolbar?.let { it.visibility = View.GONE }
            }
        }
    }


}