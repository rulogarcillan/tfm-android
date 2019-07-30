package tuppersoft.com.adoptme.features.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import tuppersoft.com.domain.entities.RecordDto

class AnimalsPagerAdapter(fm: FragmentManager, private val list: MutableList<RecordDto>) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = list.size

    override fun getItem(position: Int): Fragment = AnimalsDummyFragment.newInstance(list[position])

}