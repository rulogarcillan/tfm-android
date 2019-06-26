package tuppersoft.com.adoptme.features.profile


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_profile.view.*
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.extension.log
import tuppersoft.com.adoptme.core.extension.observe
import tuppersoft.com.adoptme.core.extension.viewModel
import tuppersoft.com.adoptme.core.navigation.Navigation
import tuppersoft.com.adoptme.core.platform.GlobalFragment


class ProfileFragment : GlobalFragment() {

    lateinit var profileViewModel: ProfileViewModel

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appComponent.inject(this)
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        configStatusBar()
        initViewModel()
        return view
    }

    private fun initViewModel() {
        profileViewModel = viewModel {
            observe(logoutState, ::handleLogOut)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.rvListMenu.layoutManager = LinearLayoutManager(context)
        view.rvListMenu.adapter = ProfileAdapter(createListMenu()) { option ->

            when (option.id) {
                ProfileItemMenu.MENU_PERSONAL_DATA -> "TODO".log()
                ProfileItemMenu.MENU_TERMS -> "TODO".log()
                ProfileItemMenu.MENU_LICENSE -> "TODO".log()
                ProfileItemMenu.MENU_CHANGELOG -> "TODO".log()
                ProfileItemMenu.MENU_LOGOUT -> profileViewModel.logout()
            }
        }
    }

    private fun handleLogOut(state: Boolean) {
        if (state) {
            activity?.let { mActivity -> Navigation.goLoginActivity(mActivity) }
        }
    }


    private fun createListMenu(): MutableList<ProfileItemMenu> {
        val list = mutableListOf<ProfileItemMenu>()

        list.add(ProfileItemMenu(ProfileItemMenu.MENU_PERSONAL_DATA, R.drawable.ic_people, R.string.menu_people))
        list.add(ProfileItemMenu(ProfileItemMenu.MENU_TERMS, R.drawable.ic_terms, R.string.menu_term))
        list.add(ProfileItemMenu(ProfileItemMenu.MENU_LICENSE, R.drawable.ic_copyleft, R.string.menu_copyleft))
        list.add(ProfileItemMenu(ProfileItemMenu.MENU_CHANGELOG, R.drawable.ic_changelog, R.string.menu_changelog))
        list.add(ProfileItemMenu(ProfileItemMenu.MENU_LOGOUT, R.drawable.ic_logout, R.string.menu_logout, false))

        return list
    }


    private fun configStatusBar() {
        val mActivity = activity
        mActivity?.let {
            val window = mActivity.window
            window.statusBarColor = ContextCompat.getColor(mActivity, tuppersoft.com.adoptme.R.color.primaryBlue)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val decor = window.decorView
                decor.systemUiVisibility = 0

            }
        }
    }


}
