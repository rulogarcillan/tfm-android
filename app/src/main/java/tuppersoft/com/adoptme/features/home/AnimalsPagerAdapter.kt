package tuppersoft.com.adoptme.features.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import tuppersoft.com.domain.entities.RecordDto

class AnimalsPagerAdapter(fm: FragmentManager, private val list: MutableList<RecordDto>, private val showName: Boolean = true) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = list.size

    override fun getItem(position: Int): Fragment = AnimalsDummyFragment.newInstance(list[position], showName)
}