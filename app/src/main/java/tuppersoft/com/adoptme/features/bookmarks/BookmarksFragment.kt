package tuppersoft.com.adoptme.features.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_toolbar_center.tvTittle
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.main.MainActivity

class BookmarksFragment : GlobalFragment() {


    lateinit var tittle: String

    companion object {
        private const val TITTLE = "title"

        @JvmStatic
        fun newInstance(tittle: Int) =
            BookmarksFragment().apply {
                arguments = Bundle().apply {
                    putInt(TITTLE, tittle)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val mContext = context
            mContext?.let { context ->
                tittle = context.getString(arguments?.getInt(TITTLE) ?: 0)
                (context as MainActivity).tvTittle.text = tittle
            }
        }
    }
}
