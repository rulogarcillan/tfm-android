package tuppersoft.com.adoptme.features.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import kotlinx.android.synthetic.main.animals_list.view.rvGlobal
import kotlinx.android.synthetic.main.animals_list.view.tvAnimalType
import kotlinx.android.synthetic.main.fragment_home.view.efbAdd
import kotlinx.android.synthetic.main.fragment_home.view.icCats
import kotlinx.android.synthetic.main.fragment_home.view.icDogs
import kotlinx.android.synthetic.main.fragment_home.view.icOthers
import kotlinx.android.synthetic.main.fragment_home.view.icRabbits
import kotlinx.android.synthetic.main.fragment_home.view.idViewPager
import kotlinx.android.synthetic.main.fragment_home.view.nsView
import kotlinx.android.synthetic.main.fragment_home.view.tab_layout
import kotlinx.android.synthetic.main.view_toolbar_center.tvTittle
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.domain.entities.Animal.CAT
import tuppersoft.com.domain.entities.Animal.DOG
import tuppersoft.com.domain.entities.Animal.OTHER
import tuppersoft.com.domain.entities.Animal.RABBIT
import tuppersoft.com.domain.entities.RecordDto
import javax.inject.Inject

class HomeFragment : GlobalFragment(), AnimalsActions {

    private lateinit var tittle: String
    private val pages: MutableList<RecordDto> = mutableListOf()
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
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.efbAdd.setOnClickListener { activity?.let { Navigation.goAddActivity(it) } }
        initViewModel()
        initIndicator()
        initAdapter()
        hideShowFab()
        mView.icCats.visibility = View.GONE
        mView.icDogs.visibility = View.GONE
        mView.icOthers.visibility = View.GONE
        mView.icRabbits.visibility = View.GONE

        val mHandler = Handler()
        val mTicker = object : Runnable {
            var page = 0
            override fun run() {
                if (mView.idViewPager.adapter?.count == page) {
                    page = 0
                } else {
                    page++
                }
                mView.idViewPager.setCurrentItem(page, true)
                mHandler.postDelayed(this, 5000)
            }
        }
        mTicker.run()
    }

    override fun onResume() {
        super.onResume()

        arguments?.let {
            val mContext = context
            mContext?.let { context ->
                tittle = context.getString(arguments?.getInt(TITTLE) ?: 0)
                (context as MainActivity).tvTittle.text = tittle
            }
        }

        homeViewModel.getAllRecords(null)
        homeViewModel.getAllRecords(DOG)
        homeViewModel.getAllRecords(CAT)
        homeViewModel.getAllRecords(RABBIT)
        homeViewModel.getAllRecords(OTHER)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        mView = inflater.inflate(R.layout.fragment_home, container, false)
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
            observe(dogs, ::handleDogs)
            observe(cats, ::handleCats)
            observe(rabbits, ::handleRabbits)
            observe(others, ::handleOthers)
        }
    }

    private fun handleDogs(dogs: MutableList<RecordDto>) {
        mView.icDogs.tvAnimalType.text = getString(R.string.last_dogs)
        mView.icDogs.rvGlobal.isNestedScrollingEnabled = false
        mView.icDogs.rvGlobal.setHasFixedSize(false)
        if (dogs.size > 0) {
            mView.icDogs.rvGlobal.adapter = AnimalsCardAdapter(dogs, this)
            mView.icDogs.visibility = View.VISIBLE
        } else {
            mView.icDogs.visibility = View.GONE
        }
    }

    private fun handleCats(cats: MutableList<RecordDto>) {
        mView.icCats.tvAnimalType.text = getString(R.string.last_cats)
        mView.icCats.rvGlobal.isNestedScrollingEnabled = false
        mView.icCats.rvGlobal.setHasFixedSize(false)
        if (cats.size > 0) {
            mView.icCats.rvGlobal.adapter = AnimalsCardAdapter(cats, this)
            mView.icCats.visibility = View.VISIBLE
        } else {
            mView.icCats.visibility = View.GONE
        }
    }

    private fun handleRabbits(rabbits: MutableList<RecordDto>) {
        mView.icRabbits.tvAnimalType.text = getString(R.string.last_rabbits)
        mView.icRabbits.rvGlobal.isNestedScrollingEnabled = false
        mView.icRabbits.rvGlobal.setHasFixedSize(false)
        if (rabbits.size > 0) {
            mView.icRabbits.rvGlobal.adapter = AnimalsCardAdapter(rabbits, this)
            mView.icRabbits.visibility = View.VISIBLE
        } else {
            mView.icRabbits.visibility = View.GONE
        }
    }

    private fun handleOthers(others: MutableList<RecordDto>) {

        mView.icOthers.tvAnimalType.text = getString(R.string.last_others_animal)

        mView.icOthers.rvGlobal.isNestedScrollingEnabled = false
        mView.icOthers.rvGlobal.setHasFixedSize(false)
        if (others.size > 0) {
            mView.icOthers.rvGlobal.adapter = AnimalsCardAdapter(others, this)
            mView.icOthers.visibility = View.VISIBLE
        } else {
            mView.icOthers.visibility = View.GONE
        }
    }

    private fun hideShowFab() {

        mView.nsView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                mView.efbAdd.hide()
            } else {
                mView.efbAdd.show()
            }
        })
    }

    override fun onClick(recordDto: RecordDto) {
        fragmentManager?.let {
            Navigation.goAnimalProfileFragment(it, recordDto)
        }
    }
}
