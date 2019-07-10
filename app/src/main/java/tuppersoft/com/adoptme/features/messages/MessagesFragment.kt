package tuppersoft.com.adoptme.features.messages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_toolbar.view.*
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.home.HomeFragment


class MessagesFragment : GlobalFragment() {


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
            }
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.toolbar.title = tittle
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

}
