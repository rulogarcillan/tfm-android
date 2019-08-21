package tuppersoft.com.adoptme.features.chat

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.etChat
import kotlinx.android.synthetic.main.activity_chat.rvChat
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
    private val user: UserDto by lazy {
        SharedPreferencesRepository.loadPreferenceObject(
            this,
            "USER",
            UserDto()
        ) as UserDto
    }

    lateinit var otherUser: UserDto

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
        chatViewModel.getUser(chatId.split("-").filterNot { it.contentEquals(user.uid) }[0])

    }

    private fun initChat() {
        rvChat.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = ChatAdapter(mutableListOf(), user.uid, otherUser)
        }
    }

    fun sendMessage(view: View) {
        if (etChat.text.isNotEmpty()) {
            val msg = MessageDto(etChat.text.toString(), user.uid)
            chatViewModel.sendMessage(msg, chatId)
            etChat.setText("")
        }
    }

    fun handleChats(listMsg: MutableList<MessageDto>) {
        listMsg.forEach { it.timeStamp.toString().log() }
        (rvChat.adapter as ChatAdapter).items = listMsg
        (rvChat.adapter as ChatAdapter).notifyDataSetChanged()
        rvChat.scrollToPosition(listMsg.size - 1)
    }

    fun handleOtherUser(user: UserDto) {
        otherUser = user
        initChat()
        chatViewModel.initChat(chatId)
    }

    private fun initViewModel() {
        chatViewModel = viewModel(viewModelFactory) {
            observe(msgListLive, ::handleChats)
            observe(user, ::handleOtherUser)
        }
    }

    private fun getExtras() {
        chatId = intent.extras?.getString(CHAT_ID) ?: ""
        if (chatId == "") {
            finish()
        }
    }
}
