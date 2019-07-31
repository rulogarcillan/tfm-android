package tuppersoft.com.adoptme.features.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.efbAdd
import kotlinx.android.synthetic.main.fragment_home.view.idViewPager
import kotlinx.android.synthetic.main.fragment_home.view.tab_layout
import kotlinx.android.synthetic.main.view_toolbar_center.tvTittle
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.log
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.domain.entities.RecordDto
import javax.inject.Inject

class HomeFragment : GlobalFragment() {


    private lateinit var tittle: String
    val pages: MutableList<RecordDto> = mutableListOf()
    lateinit var mView: View

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var homeViewModel: HomeViewModel

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
        initViewModel()
        initIndicator()
        initAdapter()
        homeViewModel.getRecords()

        val mHandler = Handler()
        val mTicker = object : Runnable {
            var page = 0
            override fun run() {
                if (mView.idViewPager.adapter?.count == page) {
                    page=0
                } else {
                    page++
                }
                "aa".log()
                mView.idViewPager.setCurrentItem(page, true)
                mHandler.postDelayed(this, 5000)
            }
        }
        mTicker.run()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        mView = inflater.inflate(tuppersoft.com.adoptme.R.layout.fragment_home, container, false)
        return mView
    }

    private fun handlePages(records: MutableList<RecordDto>) {
        pages.clear()
        if (records.size > 4) {
            for (i in 0..4) {
                var item = records.random()
                while (pages.contains(item)) {
                    item = records.random()
                }
                pages.add(item)
            }
        } else {
            pages.addAll(records)
        }
        initAdapter()
    }

    private fun initIndicator() {
        mView.tab_layout.setupWithViewPager(mView.idViewPager, true)
    }

    private fun initAdapter() {
        activity?.let {
            mView.idViewPager.adapter = AnimalsPagerAdapter(it.supportFragmentManager, pages)
        }
    }

    private fun initViewModel() {
        homeViewModel = viewModel(viewModelFactory) {
            observe(records, ::handlePages)
        }
    }
}
