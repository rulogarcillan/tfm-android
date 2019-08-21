package tuppersoft.com.adoptme.features.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_messages.rvChats
import kotlinx.android.synthetic.main.view_toolbar_center.tvTittle
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.sortCombine
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalFragment
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.data.repositories.SharedPreferencesRepository
import tuppersoft.com.domain.entities.UserDto
import javax.inject.Inject

class MessagesFragment : GlobalFragment() {

    lateinit var tittle: String
    private val user: UserDto by lazy {
        SharedPreferencesRepository.loadPreferenceObject(
            requireContext(),
            "USER",
            UserDto()
        ) as UserDto
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var messageViewModel: MessageViewModel

    companion object {
        private const val TITTLE = "title"

        @JvmStatic
        fun newInstance(tittle: Int) =
            MessagesFragment().apply {
                arguments = Bundle().apply {
                    putInt(TITTLE, tittle)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun initViewModel() {
        messageViewModel = viewModel(viewModelFactory) {
            observe(listChats, ::handleChats)

        }
    }

    fun handleChats(list: MutableList<UserDto>) {

        (rvChats.adapter as ChatsAdapter).list = list
        (rvChats.adapter as ChatsAdapter).notifyDataSetChanged()
    }

    private fun initChat() {
        rvChats.apply {
            layoutManager = LinearLayoutManager(this@MessagesFragment.requireContext())
            adapter = ChatsAdapter(mutableListOf()) {
                Navigation.goChatActivity(requireActivity(), it sortCombine user.uid)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initChat()
        messageViewModel.getUserChats(user.uid)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_messages, container, false)
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
