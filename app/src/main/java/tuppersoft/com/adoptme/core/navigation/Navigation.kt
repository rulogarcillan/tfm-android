package tuppersoft.com.adoptme.core.navigation

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.FragmentManager
import com.mikepenz.aboutlibraries.LibsBuilder
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.R.style
import tuppersoft.com.adoptme.core.extension.finishOrNot
import tuppersoft.com.adoptme.features.add.AddActivity
import tuppersoft.com.adoptme.features.animalProfile.AnimalProfileFragment
import tuppersoft.com.adoptme.features.bookmarks.BookmarksFragment
import tuppersoft.com.adoptme.features.chat.ChatActivity
import tuppersoft.com.adoptme.features.chat.ChatActivity.Companion.CHAT_ID
import tuppersoft.com.adoptme.features.discovery.DiscoveryFragment
import tuppersoft.com.adoptme.features.home.HomeFragment
import tuppersoft.com.adoptme.features.login.LoginActivity
import tuppersoft.com.adoptme.features.main.MainActivity
import tuppersoft.com.adoptme.features.messages.MessagesFragment
import tuppersoft.com.adoptme.features.personaldata.PersonalDataActivity
import tuppersoft.com.adoptme.features.profile.ProfileFragment
import tuppersoft.com.domain.entities.RecordDto

object Navigation {

    private const val URL_TERM_CONDITION = "https://info.tuppersoft.com/privacy/privacy_policy_adoptme.html"

    fun goLoginActivity(mActivity: Activity, finish: Boolean = true) {

        val intent = Intent(mActivity, LoginActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }

    fun goMainActivity(mActivity: Activity, finish: Boolean = true) {
        val intent = Intent(mActivity, MainActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }

    fun goAddActivity(mActivity: Activity, finish: Boolean = false) {
        val intent = Intent(mActivity, AddActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }

    fun goChatActivity(mActivity: Activity, chatId: String, finish: Boolean = false) {
        val intent = Intent(mActivity, ChatActivity::class.java)
        intent.putExtra(CHAT_ID, chatId)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }

    fun goHomeFragment(manager: FragmentManager) {
        val fragment = HomeFragment.newInstance(R.string.home)
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.idFrameLayout, fragment, HomeFragment::class.java.name)
        transaction.disallowAddToBackStack()
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        transaction.commit()
    }

    fun goDiscoveryFragment(manager: FragmentManager) {
        val fragment = DiscoveryFragment.newInstance(R.string.discovery)
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.idFrameLayout, fragment, DiscoveryFragment::class.java.name)
        transaction.disallowAddToBackStack()
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        transaction.commit()
    }

    fun goBookmarksFragment(manager: FragmentManager) {
        val fragment = BookmarksFragment.newInstance(R.string.bookmarks)
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.idFrameLayout, fragment, BookmarksFragment::class.java.name)
        transaction.disallowAddToBackStack()
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        transaction.commit()
    }

    fun goMessagesFragment(manager: FragmentManager) {
        val fragment = MessagesFragment.newInstance(R.string.messages)
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.idFrameLayout, fragment, MessagesFragment::class.java.name)
        transaction.disallowAddToBackStack()
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        transaction.commit()
    }

    fun goProfileFragment(manager: FragmentManager) {
        val fragment = ProfileFragment.newInstance()
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.idFrameLayout, fragment, ProfileFragment::class.java.name)
        transaction.disallowAddToBackStack()
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        transaction.commit()
    }

    fun goPersonalDataActivity(mActivity: Activity, finish: Boolean = true) {
        val intent = Intent(mActivity, PersonalDataActivity::class.java)
        mActivity.startActivity(intent)
        mActivity.finishOrNot(finish)
    }

    fun goAnimalProfileFragment(manager: FragmentManager, recordDto: RecordDto) {
        val fragment = AnimalProfileFragment.newInstance(recordDto)
        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.slide_out

        )
        transaction.replace(R.id.idFrameLayout, fragment)
        transaction.addToBackStack(AnimalProfileFragment::class.java.name)
        transaction.commit()
    }

    fun goLibraries(mActivity: Activity) {
        LibsBuilder()
            .withFields(tuppersoft.com.adoptme.R.string::class.java.fields)
            .withAutoDetect(true)
            .withVersionShown(true)
            .withLicenseShown(true)
            .withAboutIconShown(true)
            .withAboutVersionShown(true)
            .withAboutDescription(mActivity.resources.getString(tuppersoft.com.adoptme.R.string.written_by) + " " + "<a href='https://www.linkedin.com/in/raul-rodriguez-concepcion/'>Linkedin</a><br/><br/><b>License GNU GPL V3.0</b><br/><a href=\"https://github.com/rulogarcillan/tfm-android\">Project in Github</a>")
            .withAboutAppName(mActivity.getString(tuppersoft.com.adoptme.R.string.app_name))
            .withActivityTitle(mActivity.resources.getString(tuppersoft.com.adoptme.R.string.app_name))
            //.withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
            .withActivityTheme(style.AppTheme)
            .start(mActivity)
    }

    fun goTermCondition(mActivity: Activity) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(
            mActivity, Uri.parse(URL_TERM_CONDITION)

        )
    }
}
