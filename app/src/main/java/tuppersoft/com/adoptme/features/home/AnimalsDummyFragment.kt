package tuppersoft.com.adoptme.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.dummy_fragment.view.ivPhoto
import kotlinx.android.synthetic.main.dummy_fragment.view.tvName
import tuppersoft.com.adoptme.R
import tuppersoft.com.adoptme.core.extension.loadFromUrl
import tuppersoft.com.domain.entities.RecordDto
import tuppersoft.com.domain.entities.Sex.FEMALE

/**
 * Created by Raúl Rodríguez Concepción on 2019-07-30.
 * Talento Mobile
 * raulrcs@gmail.com
 */

class AnimalsDummyFragment : Fragment() {

    lateinit var page: RecordDto
    private var showName: Boolean = true

    companion object {
        const val DATA_ANIMAL = "DATA_ANIMAL"
        const val DATA_ANIMAL_NAME = "DATA_ANIMAL_NAME"
        fun newInstance(data: RecordDto, showName: Boolean): AnimalsDummyFragment {
            val args = Bundle()
            args.putSerializable(DATA_ANIMAL, data)
            args.putBoolean(DATA_ANIMAL_NAME, showName)
            val myFragment = AnimalsDummyFragment()
            myFragment.arguments = args
            return myFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dummy_fragment, container, false)
        getmArguments()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.ivPhoto.loadFromUrl(page.imageUrl.random())
        if (showName) {
            if (page.sex == FEMALE) {
                view.tvName.text = "${page.name.substring(0, 1).toUpperCase() + page.name.substring(1)} - ♀"
            } else {
                view.tvName.text = "${"${page.name.substring(0, 1).toUpperCase()}${page.name.substring(1)}"} - ♂"
            }
        }
    }

    private fun getmArguments() {
        page = (arguments?.getSerializable(DATA_ANIMAL) ?: RecordDto()) as RecordDto
        showName = (arguments?.getBoolean(DATA_ANIMAL_NAME)) ?: true
    }
}