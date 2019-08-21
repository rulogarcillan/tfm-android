package tuppersoft.com.adoptme.features.chat

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_chat.etChat
import kotlinx.android.synthetic.main.view_toolbar.toolbar
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.di.viewmodel.ViewModelFactory
import tuppersoft.com.adoptme.core.extension.log
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.platform.GlobalActivity
import tuppersoft.com.data.repositories.SharedPreferencesRepository
import tuppersoft.com.domain.entities.MessageDto
import tuppersoft.com.domain.entities.UserDto
import javax.inject.Inject

class ChatActivity : GlobalActivity() {

    lateinit var chatId: String
    val user: UserDto by lazy { SharedPreferencesRepository.loadPreferenceObject(this, "USER", UserDto()) as UserDto }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var chatViewModel: ChatViewModel

    companion object {
        const val CHAT_ID = "CHAT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBar?.setDisplayShowHomeEnabled(true)
        mActionBar?.title = getString(R.string.chat)

        getExtras()
        initViewModel()
        chatViewModel.initChat(chatId)
    }

    fun sendMessage(view: View) {
        if (etChat.text.isNotEmpty()) {
            val msg = MessageDto(etChat.text.toString(), user.uid)
            chatViewModel.sendMessage(msg, chatId)
        }
    }

    fun handleChats(listMsg:MutableList<MessageDto>){
        listMsg.forEach { it.timeStamp.toString().log() }
    }

    private fun initViewModel() {
        chatViewModel = viewModel(viewModelFactory) {
           observe(msgListLive, ::handleChats)
        }
    }

    private fun getExtras() {
        chatId = intent.extras?.getString(CHAT_ID) ?: ""
        if (chatId == "") {
            finish()
        }
    }
}
