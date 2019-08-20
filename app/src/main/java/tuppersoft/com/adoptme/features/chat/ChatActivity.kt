package tuppersoft.com.adoptme.features.chat

import android.os.Bundle
import kotlinx.android.synthetic.main.view_toolbar.toolbar
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.extension.log
import tuppersoft.com.adoptme.core.platform.GlobalActivity

class ChatActivity : GlobalActivity() {

    lateinit var chatId: String

    companion object {
        const val CHAT_ID = "CHAT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        getExtras()

        chatId.log()

        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)
        val mActionBar = supportActionBar
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        mActionBar?.setDisplayShowHomeEnabled(true)
        mActionBar?.title = getString(R.string.chat)
    }

    private fun getExtras() {
        chatId = intent.extras?.getString(CHAT_ID) ?: ""
        if (chatId == "") {
            finish()
        }
    }
}
