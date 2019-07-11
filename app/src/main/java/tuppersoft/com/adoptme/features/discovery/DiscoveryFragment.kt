package tuppersoft.com.adoptme.features.discovery


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_toolbar_center.*
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.home.HomeFragment
import tuppersoft.com.adoptme.features.main.MainActivity


class DiscoveryFragment : GlobalFragment() {


    lateinit var tittle: String


    companion object {
        private const val TITTLE = "title"

        @JvmStatic
        fun newInstance(tittle: Int) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(TITTLE, tittle)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val mContext = context
            mContext?.let { mContext ->
                tittle = mContext.getString(it.getInt(TITTLE))
                (context as MainActivity).tvTittle.text = tittle
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }
}
