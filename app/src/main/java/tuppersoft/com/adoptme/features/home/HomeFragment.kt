package tuppersoft.com.adoptme.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.efbAdd
import kotlinx.android.synthetic.main.fragment_home.view.idViewPager
import kotlinx.android.synthetic.main.view_toolbar_center.tvTittle
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.domain.entities.RecordDto

class HomeFragment : GlobalFragment() {


    private lateinit var tittle: String
    lateinit var pages: ArrayList<RecordDto>

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
            mContext?.let { context ->
                tittle = context.getString(it.getInt(TITTLE))
                (context as MainActivity).tvTittle.text = tittle
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.efbAdd.setOnClickListener { activity?.let { Navigation.goAddActivity(it) } }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun createPages() {
        pages = ArrayList()
        pages.add(
            RecordDto()
        )
        pages.add(
            RecordDto()
        )
    }

    /*  private fun initIndicator(view: View) {
          tab_layout.setupWithViewPager(idViewPager, true)
      }*/

    private fun initAdapter(view: View) {
        activity?.let {
            view.idViewPager.adapter = AnimalsPagerAdapter(it.supportFragmentManager, pages)
        }
    }
}
