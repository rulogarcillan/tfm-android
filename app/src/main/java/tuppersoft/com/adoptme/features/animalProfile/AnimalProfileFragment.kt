package tuppersoft.com.adoptme.features.animalProfile

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_animal_profile.view.idDelete
import kotlinx.android.synthetic.main.fragment_animal_profile.view.idMsg
import kotlinx.android.synthetic.main.fragment_animal_profile.view.ivAvatar
import kotlinx.android.synthetic.main.fragment_animal_profile.view.tvAge
import kotlinx.android.synthetic.main.fragment_animal_profile.view.tvAnimalName
import kotlinx.android.synthetic.main.fragment_animal_profile.view.tvName
import kotlinx.android.synthetic.main.fragment_animal_profile.view.tvSex
import kotlinx.android.synthetic.main.fragment_home.view.idViewPager
import kotlinx.android.synthetic.main.fragment_home.view.tab_layout
import kotlinx.android.synthetic.main.view_toolbar_center.tvTittle
import tuppersoft.com.adoptme.R.layout
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.loadFromUrl
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.home.AnimalsPagerAdapter
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.data.repositories.SharedPreferencesRepository
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.UserDto
import javax.inject.Inject

class AnimalProfileFragment : GlobalFragment() {

    lateinit var recordDto: RecordDto
    val user: UserDto by lazy { SharedPreferencesRepository.loadPreferenceObject(requireContext(), "USER", UserDto()) as UserDto }
    var isMyAnimal = false
    lateinit var mView: View

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var animalsProfileViewModel: AnimalsProfileViewModel

    companion object {
        private const val ANIMAL = "ANIMAL"

        @JvmStatic
        fun newInstance(recordDto: RecordDto) =
            AnimalProfileFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ANIMAL, recordDto)
                }
            }
    }

    private fun initIndicator(mView: View) {
        mView.tab_layout.setupWithViewPager(mView.idViewPager, true)
    }

    private fun initAdapter(mView: View) {
        val pages: MutableList<RecordDto> = mutableListOf()

        recordDto.imageUrl.forEach {
            pages.add(recordDto.copy(imageUrl = mutableListOf(it)))
        }

        activity?.let {
            mView.idViewPager.adapter = AnimalsPagerAdapter(it.supportFragmentManager, pages, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initAdapter(view)
        initIndicator(view)
        manageButton(view)

        view.tvAnimalName.text = recordDto.name
        view.tvAge.text = recordDto.age.toString()
        view.tvSex.text = recordDto.sex.name


        animalsProfileViewModel.getUser(recordDto.uid)

        val mHandler = Handler()
        val mTicker = object : Runnable {
            var page = 0
            override fun run() {
                if (view.idViewPager.adapter?.count == page) {
                    page = 0
                } else {
                    page++
                }
                view.idViewPager.setCurrentItem(page, true)
                mHandler.postDelayed(this, 5000)
            }
        }
        mTicker.run()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        mView = inflater.inflate(layout.fragment_animal_profile, container, false)
        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getmArguments() {
        recordDto = (arguments?.getSerializable(ANIMAL) ?: RecordDto()) as RecordDto
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getmArguments()
        isMyAnimal = user.uid == recordDto.uid
        (context as MainActivity).tvTittle.text = recordDto.name
    }

    private fun initViewModel() {
        animalsProfileViewModel = viewModel(viewModelFactory) {
            observe(user, ::handleGetUserProfile)
        }
    }

    fun handleGetUserProfile(userDto: UserDto?) {
        mView.ivAvatar.loadFromUrl(userDto?.photoUrl)
        mView.tvName.text = userDto?.name
    }

    private fun manageButton(view: View) {
        when (recordDto.isDeleted) {
            true -> {
                view.idMsg.visibility = View.GONE
                view.idDelete.visibility = View.GONE
            }
            false -> {
                if (isMyAnimal) {
                    view.idMsg.visibility = View.GONE
                    view.idDelete.visibility = View.VISIBLE
                } else {
                    view.idMsg.visibility = View.VISIBLE
                    view.idDelete.visibility = View.GONE
                }
            }
        }
    }
}
